# KMM-Jwt-Parser

<B>Kotlin multiplatform Library</B> used to parse ```JWT``` token and return it as a ```JsonObject```

For iOS target jwt token parsing is done in ```Swfit``` side and then it is bundled using ```cinterop``` in order to call ```swift``` code from ```Kotlin```

Sample usage (in that case it is published in ```localMavenRepository```)
inside your project

```
val parser = JwtParser()
val jsonObject = parser.parse(YOUR_JWT_TOKEN)
```

```
repositories {
    mavenCentral()
    mavenLocal()
}
```


Gradle

```
sourceSets {
    val commonMain by getting {
        dependencies {
           implementation("ge.dev.jwt:JwtParser:0.9.1")
        }
    }
    
    val androidMain by getting {
            dependencies {
               implementation("ge.dev.jwt:JwtParser-jvm:0.9.1")
         }
    }

    val iosX64Main by getting {
        dependencies {
            implementation("ge.dev.jwt:JwtParser-iosx64:0.9.1")
        }
    }
    
    val iosArm64Main by getting {
        dependencies {
            implementation("ge.dev.jwt:JwtParser-iosarm64:0.9.1")
        }
    }
}
```

