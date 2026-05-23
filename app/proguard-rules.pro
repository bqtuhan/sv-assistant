# Tink (Google ErrorProne) missing classes
-dontwarn com.google.errorprone.annotations.**

# SLF4J missing classes
-dontwarn org.slf4j.impl.StaticLoggerBinder

# Keep Tink classes
-keep class com.google.crypto.tink.** { *; }
