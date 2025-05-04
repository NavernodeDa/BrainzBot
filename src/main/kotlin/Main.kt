@file:Suppress("ktlint:standard:no-wildcard-imports")

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.logging.LogLevel
import com.natpryce.konfig.ConfigurationProperties
import dataClasses.Data
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.*

fun main(args: Array<String>): Unit =
    runBlocking {
        val config = ConfigurationProperties.fromResource("config.properties")
        val logger = LoggerFactory.getLogger("Logger")
        val stringFile =
            when (args.first().lowercase()) {
                "russian" -> "strings_ru"
                "ukrainian" -> "strings_ua"
                "english" -> "strings_en"
                else -> "strings_${args.first().lowercase()}"
            } + ".json"

        launch {
            val instructions = Brainz(config, logger, stringFile)

            val chatId = ChatId.fromId(config[Data.chatId])
            val messageId = config[Data.messageId]

            val bot = bot {
                token = config[Data.tokenBot]
                logLevel = LogLevel.Error
                dispatch {
                    fun warnUserIsNull() = logger.warn("message.from is null")
                    command("update") {
                        message.from?.let {
                            bot.editMessageText(
                                chatId = chatId,
                                messageId = messageId,
                                text = instructions.buildText(),
                                parseMode = ParseMode.HTML,
                                disableWebPagePreview = true
                            )
                            bot.forwardMessage(
                                ChatId.fromId(message.from!!.id),
                                chatId,
                                messageId = messageId
                            )
                        } ?: run {
                            warnUserIsNull()
                        }
                    }
                }
            }

            fun getTime() = SimpleDateFormat("HH:mm:ss").format(Date().time)
            CoroutineScope(Dispatchers.Default + SupervisorJob()).launch {
                var cache = ""
                while (true) {
                    val text = instructions.buildText()
                    if (cache != text) {
                        cache = text
                        bot.editMessageText(
                            chatId = chatId,
                            messageId = messageId,
                            text = instructions.buildText(),
                            parseMode = ParseMode.HTML,
                            disableWebPagePreview = true
                        )
                        logger.info("${getTime()} - message is updated")
                    }
                    delay(config[Data.updateInterval] * 1000)
                }
            }

            bot.startPolling()
            logger.info("Bot is started")
        }

    }
