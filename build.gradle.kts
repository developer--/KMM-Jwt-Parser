plugins {
    kotlin("multiplatform") version "1.9.21"
    id("convention.publication")
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
}

group = "io.github.developer--"
version = "1.0.0"

repositories {
    gradlePluginPortal()
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
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}