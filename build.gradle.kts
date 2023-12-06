plugins {
    kotlin("multiplatform") version "1.9.21"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
//    `kotlin-dsl`
    `maven-publish`
}

group = "io.github.developer--"
version = "1.0.0"
//group = PUBLISH_GROUP_ID
//version = PUBLISH_VERSION

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

apply {
    from("${rootDir}/publish-root.gradle")
    from("${rootDir}/publish-module.gradle")
}