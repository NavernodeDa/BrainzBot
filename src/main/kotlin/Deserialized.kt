import dataClasses.Strings
import kotlinx.serialization.json.Json
import org.slf4j.Logger

class Deserialized(
    private val logger: Logger,
) {
    fun getDeserialized(fileName: String): Strings? {
        val file = Deserialized::class.java.getResource(fileName)?.readText()

        return if (file != null) {
            Json.decodeFromString<Strings>(file)
        } else {
            logger.error("$fileName is null")
            null
        }
    }
}
