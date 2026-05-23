package io.github.bqtuhan.svassistant.core.parser

import org.xmlpull.v1.XmlPullParser

/**
 * Recursively skips the current tag and all its children.
 * Prevents infinite loops on empty tags like <stats/> or malformed XML.
 */
fun XmlPullParser.skip() {
    if (eventType != XmlPullParser.START_TAG) {
        throw IllegalStateException("skip() must be called on START_TAG")
    }
    var depth = 1
    while (depth != 0) {
        when (next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}

/**
 * Safely reads the text content of a simple tag, handling empty tags <tag/> gracefully.
 * Leaves the parser positioned on the END_TAG of the element.
 */
fun XmlPullParser.readSimpleText(): String {
    val builder = StringBuilder()
    var depth = 1
    while (depth > 0) {
        when (next()) {
            XmlPullParser.TEXT -> builder.append(text)
            XmlPullParser.START_TAG -> depth++
            XmlPullParser.END_TAG -> depth--
        }
    }
    return builder.toString().trim()
}