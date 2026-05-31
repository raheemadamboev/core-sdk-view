plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("maven-publish")
}

android {
    namespace = "xyz.teamgravity.coresdkview"

    compileSdk {
        version = release(libs.versions.sdk.compile.get().toInt()) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    lint {
        targetSdk = libs.versions.sdk.target.get().toInt()
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    // core
    implementation(libs.core)

    // material
    implementation(libs.material)

    // fragment
    implementation(libs.fragment)

    // coroutines
    implementation(libs.coroutines)
    implementation(libs.coroutines.android)

    // lifecycle
    implementation(libs.lifecycle)
    implementation(libs.lifecycle.viewmodel)

    // hilt
    implementation(libs.hilt)
    ksp(libs.dagger.compiler)

    // navigation
    implementation(libs.navigation.fragment)

    // progressbar
    implementation(libs.progressbar.circular)

    // konfetti
    implementation(libs.konfetti)

    // timber
    implementation(libs.timber)

    // gravity
    implementation(libs.gravity.core)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.raheemadamboev"
            artifactId = "core-sdk-view"
            version = "1.0.7"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}