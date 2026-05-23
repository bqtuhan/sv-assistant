# Tink (Google ErrorProne) missing classes
-dontwarn com.google.errorprone.annotations.**

# SLF4J missing classes
-dontwarn org.slf4j.impl.StaticLoggerBinder

# Google API Client missing classes (referenced by Tink)
-dontwarn com.google.api.client.**

# Joda-Time missing classes (referenced by Tink)
-dontwarn org.joda.time.**

# Keep Tink classes
-keep class com.google.crypto.tink.** { *; }
