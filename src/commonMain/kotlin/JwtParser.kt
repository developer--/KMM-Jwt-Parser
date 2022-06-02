import kotlinx.serialization.json.JsonObject

expect class JwtParser() {
  fun parseToJsonObject(jwtToken: String): JsonObject?
}