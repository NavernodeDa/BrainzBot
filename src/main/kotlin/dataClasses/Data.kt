@file:Suppress("ktlint:standard:no-wildcard-imports")

package dataClasses

import com.natpryce.konfig.*

object Data : PropertyGroup() {
    val apiKey by stringType
    val user by stringType
    val tokenBot by stringType
    val chatId by longType
    val messageId by longType
    val userAgent by stringType
    val updateInterval by longType
    val limitForArtists by intType
    val limitForTracks by intType
}
