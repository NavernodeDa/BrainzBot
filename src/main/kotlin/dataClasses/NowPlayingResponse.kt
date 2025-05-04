package dev.kumchatka

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingResponse(
    val payload: NowPlayingData,
)

@Serializable
data class NowPlayingData(
    val count: Int,
    val listens: List<NowPlayingListen>,
    @SerialName("playing_now") val playingNow: Boolean,
    @SerialName("user_id") val userId: String,
)

@Serializable
data class NowPlayingListen(
    @SerialName("playing_now") val playingNow: Boolean,
    @SerialName("track_metadata") val trackMetadata: TrackMetadataNowPlaying,
)

@Serializable
data class TrackMetadataNowPlaying(
    @SerialName("artist_name") val artistName: String,
    @SerialName("release_name") val releaseName: String,
    @SerialName("track_name") val trackName: String,
    @SerialName("additional_info") val additionalInfo: AdditionalInfoNowPlaying,
)

@Serializable
data class AdditionalInfoNowPlaying(
    @SerialName("media_player") val mediaPlayer: String? = null,
    @SerialName("submission_client") val submissionClient: String? = null,
    @SerialName("release_mbid") val releaseMbid: String? = null,
    @SerialName("artist_mbids") val artistMbids: List<String> = emptyList(),
    @SerialName("recording_mbid") val recordingMbid: String? = null,
    val tags: List<String> = emptyList(),
    val duration: Int? = null,
    @SerialName("tracknumber") val trackNumber: Int? = null,
    @SerialName("work_mbids") val workMbids: List<String> = emptyList(),
    @SerialName("release_artist_names") val releaseArtistNames: List<String> = emptyList(),
    @SerialName("spotify_album_artist_ids") val spotifyAlbumArtistIds: List<String> = emptyList(),
    @SerialName("spotify_artist_ids") val spotifyArtistIds: List<String> = emptyList(),
    @SerialName("artist_names") val artistNames: List<String> = emptyList(),
)
