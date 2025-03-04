plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    id(libs.plugins.safeArgs.get().pluginId)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.room.plugin)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.tbcacademyhomework"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tbcacademyhomework"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://themealdb.com/api/json/v1/1/\"")
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://themealdb.com/api/json/v1/1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    kapt {
        correctErrorTypes = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    implementation(libs.glide)
    implementation(libs.preferences.datastore)
    implementation(libs.lottie)
    implementation(libs.dagger.hilt.android)
    implementation(libs.retrofit.serialization)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.serialization.json)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.shimmer)
    implementation(libs.firebase.auth)
    implementation(libs.splash.screen)
    kapt(libs.dagger.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}