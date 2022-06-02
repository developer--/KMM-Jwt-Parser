import kotlinx.serialization.json.JsonObject

actual class JwtParser {
    actual fun parseToJsonObject(jwtToken: String): JsonObject? {
        return JWTParserHelper.getPayload(jwtToken)
    }
}

