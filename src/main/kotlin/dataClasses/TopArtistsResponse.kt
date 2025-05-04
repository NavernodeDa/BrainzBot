package dev.kumchatka

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopArtistsResponse(
    val payload: Payload,
)

@Serializable
data class Payload(
    val artists: List<Artist>,
    val count: Int,
    @SerialName("from_ts") val fromTs: Long,
    @SerialName("last_updated") val lastUpdated: Long,
    val offset: Int,
    val range: String,
    @SerialName("to_ts") val toTs: Long,
    @SerialName("total_artist_count") val totalArtistCount: Int,
    @SerialName("user_id") val userId: String,
)

@Serializable
data class Artist(
    @SerialName("artist_mbid") val artistMbid: String? = null, // может отсутствовать
    @SerialName("artist_name") val artistName: String,
    @SerialName("listen_count") val listenCount: Int,
)
