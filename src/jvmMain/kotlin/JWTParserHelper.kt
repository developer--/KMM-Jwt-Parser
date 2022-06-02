import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.nio.charset.StandardCharsets

/**
 * Utility class for all operations on JWT.
 */
internal object JWTParserHelper {
  private const val HEADER = 0
  private const val PAYLOAD = 1
  private const val SIGNATURE = 2
  private const val JWT_PARTS = 3

  /**
   * Returns payload of a JWT as a JSON object.
   *
   * @param jwt       REQUIRED: valid JSON Web Token as String.
   * @return payload as a JSONObject.
   */
  @Throws(Exception::class)
  fun getPayload(jwt: String): JsonObject? {
    try {
      validateJWT(jwt)
      val payload = jwt.split("\\.".toRegex()).toTypedArray()[PAYLOAD]
      val sectionDecoded = java.util.Base64.getDecoder().decode(payload)
      val jwtSection = String(sectionDecoded, StandardCharsets.UTF_8)
      return Json.decodeFromString<JsonObject>(jwtSection)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    return null
  }

  /**
   * Checks if `JWT` is a valid JSON Web Token.
   *
   * @param jwt REQUIRED: The JWT as a [String].
   */
  @Throws(Exception::class)
  fun validateJWT(jwt: String) {
    // Check if the the JWT has the three parts
    val jwtParts = jwt.split("\\.".toRegex()).toTypedArray()
    if (jwtParts.size != JWT_PARTS) {
      throw Exception()
    }
  }
}
