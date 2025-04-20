import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.androidx.activity.compose)
            implementation(libs.jetbrains.kotlinx.serialization.json)
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.1.2")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.shared)
            implementation(projects.coreNetwork)
            implementation(libs.compottie)
            implementation(libs.compottie.network)

        }
    }
}

android {
    namespace = "com.jetbrains.greeting"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jetbrains.greeting"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        buildConfigField(
            "String",
            "twelveDataApiKey",
            "\"${project.findProperty("twelveDataApiKey")}\""
        )
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    debugImplementation("com.github.chuckerteam.chucker:library:4.1.0")
    debugImplementation(compose.uiTooling)
//    implementation(libs)
}

