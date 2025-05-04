package dev.kumchatka

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentTracksResponse(
    val payload: ListenData,
)

@Serializable
data class ListenData(
    val count: Int,
    @SerialName("latest_listen_ts") val latestListenTs: Long,
    val listens: List<Listen>,
    @SerialName("oldest_listen_ts") val oldestListenTs: Long,
    @SerialName("user_id") val userId: String,
)

@Serializable
data class Listen(
    @SerialName("inserted_at") val insertedAt: Long,
    @SerialName("listened_at") val listenedAt: Long,
    @SerialName("recording_msid") val recordingMsid: String,
    @SerialName("track_metadata") val trackMetadata: TrackMetadata,
    @SerialName("user_name") val userName: String,
)

@Serializable
data class TrackMetadata(
    @SerialName("artist_name") val artistName: String,
    @SerialName("release_name") val releaseName: String,
    @SerialName("track_name") val trackName: String,
    @SerialName("additional_info") val additionalInfo: AdditionalInfo,
    @SerialName("mbid_mapping") val mbidMapping: MbidMapping? = null,
)

@Serializable
data class AdditionalInfo(
    @SerialName("submission_client") val submissionClient: String? = null,
    @SerialName("media_player") val mediaPlayer: String? = null,
    @SerialName("artist_mbids") val artistMbids: List<String> = emptyList(),
    @SerialName("recording_msid") val recordingMsid: String? = null,
    @SerialName("tags") val tags: List<String> = emptyList(),
    @SerialName("duration") val duration: Int? = null,
    @SerialName("tracknumber") val trackNumber: Int? = null,
    @SerialName("work_mbids") val workMbids: List<String> = emptyList(),
    @SerialName("release_artist_names") val releaseArtistNames: List<String> = emptyList(),
    @SerialName("spotify_album_artist_ids") val spotifyAlbumArtistIds: List<String> = emptyList(),
    @SerialName("spotify_artist_ids") val spotifyArtistIds: List<String> = emptyList(),
    @SerialName("artist_names") val artistNames: List<String> = emptyList(),
    @SerialName("lastfm_artist_mbid") val lastfmArtistMbid: String? = null,
    @SerialName("release_mbid") val releaseMbid: String? = null,
)

@Serializable
data class MbidMapping(
    @SerialName("artist_mbids") val artistMbids: List<String> = emptyList(),
    val artists: List<MbidArtist> = emptyList(),
    @SerialName("recording_mbid") val recordingMbid: String? = null,
    @SerialName("recording_name") val recordingName: String? = null,
    @SerialName("release_mbid") val releaseMbid: String? = null,
    @SerialName("caa_id") val caaId: Int? = null,
    @SerialName("caa_release_mbid") val caaReleaseMbid: String? = null,
)

@Serializable
data class MbidArtist(
    @SerialName("artist_credit_name") val artistCreditName: String,
    @SerialName("artist_mbid") val artistMbid: String,
    @SerialName("join_phrase") val joinPhrase: String,
)
