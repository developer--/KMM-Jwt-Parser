plugins {
    kotlin("multiplatform") version "1.6.21"
    `maven-publish`
}

group = "ge.dev.jwt"
version = "0.9.1"

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    ios {
        val platform = when (name) {
            "iosX64" -> "iphonesimulator"
            "iosArm64" -> "iphoneos"
            else -> error("Unsupported target $name")
        }
        compilations.getByName("main") {
            cinterops.create("SwiftJwt") {
                extraOpts("-libraryPath", "$rootDir/SwiftJwt/build/Release-$platform")
                val interopTask = tasks[interopProcessingTaskName]
                interopTask.dependsOn(":SwiftJwt:build${platform.capitalize()}")
                includeDirs("$rootDir/SwiftJwt/build/Release-$platform/include")
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
            }
        }
        getByName("commonTest").dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }

        getByName("jvmTest").dependencies {
            implementation(kotlin("test-junit"))
        }
    }
}