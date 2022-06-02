listOf("iphoneos", "iphonesimulator").forEach { sdk ->
    tasks.create<Exec>("build${sdk.capitalize()}") {
        group = "build"

        commandLine(
            "xcodebuild",
            "BITCODE_GENERATION_MODE=bitcode",
            "-project", "SwiftJwt.xcodeproj",
            "-target", "SwiftJwt",
            "-sdk", sdk
        )
        workingDir(projectDir)

        inputs.files(
            fileTree("$projectDir/SwiftJwt.xcodeproj") { exclude("**/xcuserdata") },
            fileTree("$projectDir/SwiftJwt")
        )
        outputs.files(
            fileTree("$projectDir/build/Release-${sdk}")
        )
    }
}

tasks.create<Delete>("clean") {
    group = "build"

    delete("$projectDir/build")
}
