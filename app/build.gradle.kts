plugins {
    alias(libs.plugins.android)
}

android {
    namespace = "xyz.teamgravity.coresdkdemoapp"

    compileSdk {
        version = release(libs.versions.sdk.compile.get().toInt()) {
            minorApiLevel = 0
        }
    }

    defaultConfig {
        applicationId = "xyz.teamgravity.coresdkview"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    // gravity core
    implementation(projects.coreSDKView)

    // core
    implementation(libs.core)

    // appcompat
    implementation(libs.appcompat)

    // material
    implementation(libs.material)
}