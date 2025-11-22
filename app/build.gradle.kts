import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.konkuk.artium"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.konkuk.artium"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "LOGIN_URL", "\"${properties["LOGIN_SERVER_URL"]}\"")
        buildConfigField("String", "DATA_URL", "\"${properties["DATA_SERVER_URL"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-P"
        freeCompilerArgs += "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Coil
    implementation(libs.coil.compose)
  //  implementation(libs.coil.okhttp) // [수정] 이전 대화에서 수정한 coil-okhttp를 반영했습니다.

    // Network & Serialization
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization.converter)
    implementation(libs.kotlinx.serialization.json)

    // 강제 고정 - 어떤 라이브러리가 1.7.3을 끌어와도 무시함
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3") {
        version {
            strictly("1.6.3")
        }
    }

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3") {
        version {
            strictly("1.6.3")
        }
    }

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-okio:1.6.3") {
        version {
            strictly("1.6.3")
        }
    }

    // Retrofit & Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // WebView
    implementation("com.google.accompanist:accompanist-webview:0.34.0")

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler) // [수정] KSP 사용 시에는 이것 하나만 필요합니다.

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Google Fonts
    implementation(libs.androidx.compose.ui.text.google.fonts)

    // Palette
    implementation("androidx.palette:palette:1.0.0")
}