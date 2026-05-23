package io.github.bqtuhan.svassistant.core.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecurityManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefsFileName = "sv_secure_prefs"

    val securePrefs: SharedPreferences by lazy {
        initEncryptedPrefs() ?: initFallbackPrefs()
    }

    private fun initEncryptedPrefs(): SharedPreferences? {
        return try {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            EncryptedSharedPreferences.create(
                context,
                prefsFileName,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: Exception) {
            // Catches KeyStoreException / AEADBadTagException on rooted or custom ROM devices
            Log.e("SecurityManager", "Keystore exception. Clearing corrupted prefs.", e)
            context.deleteSharedPreferences(prefsFileName)
            null
        }
    }

    private fun initFallbackPrefs(): SharedPreferences {
        Log.w("SecurityManager", "Falling back to standard SharedPreferences.")
        return context.getSharedPreferences("${prefsFileName}_fallback", Context.MODE_PRIVATE)
    }
}