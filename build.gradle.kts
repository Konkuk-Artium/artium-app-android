// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Android + Kotlin 관련
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    // Hilt (DI)
    alias(libs.plugins.hilt) apply false

    // KSP (코드 생성기)
    alias(libs.plugins.ksp) apply false
}