# KMM-Jwt-Parser

<B>Kotlin multiplatform Library</B> used to parse ```JWT``` token and return it as a ```JsonObject```

For iOS target jwt token parsing is done in ```Swift``` side and then it is bundled using ```cinterop``` in order to call ```swift``` code from ```Kotlin```

Sample usage (in that case it is published in ```localMavenRepository```)
inside your project

```kotlin
val parser = JwtParser()
val jsonObject = parser.parse(YOUR_JWT_TOKEN)
```

```gradle
repositories {
    mavenCentral()
    mavenLocal()
}
```


Gradle

```gradle
sourceSets {
    val commonMain by getting {
        dependencies {
           implementation("io.github.developer--:JwtParser:1.0.0")
        }
    }
}
```

