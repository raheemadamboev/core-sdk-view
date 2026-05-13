plugins {
    alias(libs.plugins.library)
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

    // navigation
    implementation(libs.navigation.fragment)

    // konfetti
    implementation(libs.konfetti)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.raheemadamboev"
            artifactId = "core-sdk-view"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}