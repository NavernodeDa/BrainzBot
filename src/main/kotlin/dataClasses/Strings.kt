package dataClasses

import kotlinx.serialization.Serializable

@Serializable
data class Strings(
    val pastSongs: String,
    val favoriteArtists: String,
    val listens: String,
    val thereIsNothingHere: String,
    val nowPlaying: String,
    val infoForAccount: String,
    val age: String,
    val gender: String,
    val subscriber: String,
    val realName: String,
    val country: String,
    val playcount: String,
    val artistCount: String,
    val trackCount: String,
    val albumCount: String,
    val playlists: String,
    val registered: String,
    val link: String,
)
