import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JwtParserTest {
  @Test
  fun testParse() {
    val parser = JwtParser()
    val result = parser.parseToJsonObject(JWT_TOKEN)
    assertNotNull(result)
    val name = result.getValue("name").jsonPrimitive.content
    val sub = result.getValue("sub").jsonPrimitive.content
    val iat = result.getValue("iat").jsonPrimitive.long
    assertEquals(name, NAME)
    assertEquals(sub, SUB)
    assertEquals(iat, IAT)
  }

  private companion object {
    const val NAME = "John Doe"
    const val SUB = "1234567890"
    const val IAT = 1516239022L
    private const val JWT_TOKEN =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
  }
}