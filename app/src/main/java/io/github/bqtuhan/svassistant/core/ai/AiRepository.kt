package io.github.bqtuhan.svassistant.core.ai

import io.github.bqtuhan.svassistant.core.security.SecurityManager
import io.github.bqtuhan.svassistant.di.IoDispatcher
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiRepository @Inject constructor(
    private val securityManager: SecurityManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun generateAdvice(context: String): String = withContext(ioDispatcher) {
        val apiKey = securityManager.securePrefs.getString("gemini_api_key", "")?.trim() ?: ""
        if (apiKey.isBlank()) return@withContext "Please enter your Gemini API Key in Settings."

        val prompt = """
            You are an expert Stardew Valley assistant. Based on the following farm data and knowledge base, provide a short, helpful, and immersive daily tip for the farmer. Keep it under 3 sentences.
            
            Farm Context:
            $context
        """.trimIndent()

        val request = GeminiRequest(
            contents = listOf(
                Content(parts = listOf(Part(text = prompt)))
            )
        )

        try {
            val response: GeminiResponse = client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$apiKey") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
            
            response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "The spirits are silent today..."
        } catch (e: Exception) {
            "Error contacting the Wizard: ${e.message}"
        }
    }
}