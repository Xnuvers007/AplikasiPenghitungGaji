plugins {
    id("com.android.application")
}

android {

    namespace = "com.yoga.aplikasipenghitunggaji"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yoga.aplikasipenghitunggaji"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    // ── Signing Config (pakai env vars dari GitHub Secrets) ──────────────
    signingConfigs {
        create("release") {
            // Keystore sudah di-decode ke file oleh workflow sebelum gradle jalan
            storeFile     = file(System.getenv("KEYSTORE_PATH") ?: "keystore/release.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
            keyAlias      = System.getenv("KEY_ALIAS")          ?: ""
            keyPassword   = System.getenv("KEY_PASSWORD")       ?: ""
        }
    }

    buildTypes {

        // ── DEBUG: cepat, tidak di-obfuscate ────────────────────────────
        debug {
            isMinifyEnabled   = false
            isShrinkResources = false
        }

        // ── RELEASE: full R8 + obfuscation + resource shrinking ──────────
        release {
            isMinifyEnabled   = true    // Aktifkan R8/ProGuard
            isShrinkResources = true    // Hapus resource yang tidak dipakai
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.cardview:cardview:1.0.0")
}