import kotlinx.serialization.json.*
import swift.SwiftJwt.JwtTokenParser

actual class JwtParser {

  private val jwtParserSwift: JwtTokenParser by lazy { JwtTokenParser() }

  actual fun parseToJsonObject(jwtToken: String): JsonObject? {
    val result = jwtParserSwift.decodeTokenWithJwtToken(jwtToken) ?: return null
    return result.toJsonObject()
  }

  private fun Any?.toJsonElement(): JsonElement {
    return when (this) {
      is Number -> JsonPrimitive(this)
      is Boolean -> JsonPrimitive(this)
      is String -> JsonPrimitive(this)
      is Array<*> -> this.toJsonArray()
      is List<*> -> this.toJsonArray()
      is Map<*, *> -> this.toJsonObject()
      is JsonElement -> this
      else -> JsonNull
    }
  }

  private fun Array<*>.toJsonArray(): JsonArray {
    val array = mutableListOf<JsonElement>()
    this.forEach { array.add(it.toJsonElement()) }
    return JsonArray(array)
  }

  private fun List<*>.toJsonArray(): JsonArray {
    val array = mutableListOf<JsonElement>()
    this.forEach { array.add(it.toJsonElement()) }
    return JsonArray(array)
  }

  private fun Map<*, *>.toJsonObject(): JsonObject {
    val map = mutableMapOf<String, JsonElement>()
    this.forEach {
      if (it.key is String) {
        map[it.key as String] = it.value.toJsonElement()
      }
    }
    return JsonObject(map)
  }

}