@file:Suppress("ktlint:standard:no-wildcard-imports")

import dev.kumchatka.*
import kotlinx.serialization.json.Json
import org.hihn.listenbrainz.ApiClient
import org.hihn.listenbrainz.Configuration
import org.hihn.listenbrainz.LbCoreApi
import org.hihn.listenbrainz.LbStatsApi

class ListenBrainzApi(
    // private val apiKey: String,
    private val user: String,
) {
    private val client: ApiClient = Configuration.getDefaultApiClient().setBasePath("https://api.listenbrainz.org/")
    private val statsApi = LbStatsApi(client)
    private val coreApi = LbCoreApi(client)

    private val json = Json { ignoreUnknownKeys = true }

    fun getTopArtists(count: Int? = 25): List<Artist> {
        val answer =
            statsApi
                .topArtistsForUser(user)
                .count(count)
                .execute()
                .toJson()
        return json.decodeFromString<TopArtistsResponse>(answer).payload.artists
    }

    fun getRecentTracks(count: Int? = 25): List<Listen> {
        val answer =
            coreApi
                .listensForUser(user)
                .count(count)
                .execute()
                .toJson()
        return json.decodeFromString<RecentTracksResponse>(answer).payload.listens
    }

    fun getNowPlaying(): NowPlayingListen? {
        val answer = coreApi.playingNowForUser(user).execute().toJson()
        return try {
            json.decodeFromString<NowPlayingResponse>(answer).payload.listens[0]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

//    fun getLovedTracks {
//
//    }
}
