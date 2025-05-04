import com.natpryce.konfig.ConfigurationProperties
import dataClasses.Data
import dataClasses.Strings
import dev.kumchatka.Artist
import dev.kumchatka.Listen
import dev.kumchatka.NowPlayingListen
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.slf4j.Logger

class Brainz(
    private val config: ConfigurationProperties,
    private val logger: Logger,
    private val stringFile: String
) {
    private val api = ListenBrainzApi(config[Data.user])
    private val deserialized: Strings by lazy { deserialize() }

    suspend fun buildText(): String = coroutineScope {
        val text = StringBuilder()

        val recentTracksDeferred = async { getRecentSongs() }
        val favoriteArtistsDeferred = async { getFavoriteArtists() }

        val recentTracks = recentTracksDeferred.await().ifEmpty { null }
        val favoriteArtists = favoriteArtistsDeferred.await().ifEmpty { null }

        val nowPlaying = getNowPlaying()

        if (nowPlaying != null) {
            text.append("${deserialized.nowPlaying}\n")
            addNowPlaying(text, nowPlaying)
        }

        text.append("\n${deserialized.pastSongs}\n")
        recentTracks?.let {
            addRecentTracks(text, it)
        } ?: run {
            logger.warn("Result of getRecentSongs() is empty")
            text.append(deserialized.thereIsNothingHere)
        }

        text.append("\n${deserialized.favoriteArtists}\n")
        favoriteArtists?.let {
            addFavoriteArtists(text, it)
        } ?: run {
            logger.warn("Result of getFavoriteArtists() is empty")
            text.append(deserialized.thereIsNothingHere)
        }

        return@coroutineScope text.toString().replace("&", "&amp;")
    }

    private fun deserialize(): Strings = try {
        Deserialized(logger).getDeserialized(stringFile)!!
    } catch (e: Exception) {
        logger.error(e.toString())
        throw e
    }

    private fun addNowPlaying(text: StringBuilder, track: NowPlayingListen) {
        val href = track.trackMetadata.additionalInfo.recordingMbid?.let {
            """<a href="https://musicbrainz.org/recording/$it">${track.trackMetadata.trackName}</a>"""
        } ?: track.trackMetadata.trackName
        text.append("${track.trackMetadata.artistName} - $href\n")
    }

    private fun addRecentTracks(text: StringBuilder, recentTracks: List<Listen>) {
        recentTracks.forEach { track ->
            val href = track.trackMetadata.mbidMapping?.recordingMbid?.let {
                """<a href="https://musicbrainz.org/recording/$it">${track.trackMetadata.trackName}</a>"""
            } ?: track.trackMetadata.trackName
            text.append("${track.trackMetadata.artistName} - $href\n")
        }
    }

    private fun addFavoriteArtists(text: StringBuilder, listArtists: List<Artist>) {
        val url = "https://musicbrainz.org/artist/"
        listArtists.forEachIndexed { index, artist ->
            text.append("""${index + 1}. <a href="$url${artist.artistMbid}">${artist.artistName}</a> - ${artist.listenCount} ${deserialized.listens}""" + "\n")
        }
    }

    private fun getFavoriteArtists(): List<Artist> = api.getTopArtists(config[Data.limitForArtists])

    private fun getRecentSongs(): List<Listen> = api.getRecentTracks(config[Data.limitForTracks])

    private fun getNowPlaying(): NowPlayingListen? = api.getNowPlaying()
}
