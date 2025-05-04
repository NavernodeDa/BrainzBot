# BrainzBot
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

Kotlin-based Telegram bot for tracking and updating information about user's music preferences based on ListenBrainz data. The bot periodically checks the user's top artists and sends updates to the specified Telegram channel or chat.

## How to use
Insert the required values into src/main/resources/config.properties:

```properties
Data.apiKey=ap1K3y40r1astFm
Data.user=user_from_last_fm
Data.tokenBot=1234567890:tokenForYourTelegramBot
Data.chatId=chatId for your chat/channel
Data.messageId=id of the message that will be changed in chat/channel
Data.userAgent=user agent for Last.fm library
Data.updateInterval=interval in minutes
Data.limitForArtists=limit for list of artists
Data.limitForTracks=limit for list of recent tracks
```
The ```chatId``` and  ```messageId``` fields are filled in together: the id of your chat/channel is inserted into chatId, and the id of the message from your chat/channel is inserted into messageId.

### ❗️Before you run the bot, add it to your chat/channel and give it admin if you add it to the  channel ❗️

If you are using Linux, you just need to run the ```startMusicBot.sh``` file (you can even move it to another location if you pass the directory with the gradlew file as an argument). Otherwise, you just need to type "gradlew run" while in the directory with the bot in your shell. Enjoy it!

## Changing the language for the message

To change the language, you can specify the language as an argument to --language when running the bot via .sh script. The list of currently available languages: English (default), Russian, Ukrainian.

### Custom localization
You can also create your own localization. To do this, change the string values from the ```strings_en.json``` file to your own and the resulting file "strings_[your_language].json". For example, "strings_Chinese.json" and pass your language to the script: "-l Chinese"

## Example with english language
![](https://github.com/user-attachments/assets/b5bbd23f-9289-4da8-ba4a-b45f87ebd5dc)

## Usage
- ![](https://avatars.githubusercontent.com/u/57418018?s=24) [Kotlin-telegram-bot](https://github.com/kotlin-telegram-bot/kotlin-telegram-bot) for Telegram API.
- ![](https://avatars.githubusercontent.com/u/1521407?s=24) [Slf4j](https://github.com/qos-ch/slf4j) for logging.
- ![](https://avatars.githubusercontent.com/u/56219?s=24) [Konfig](https://github.com/npryce/konfig) for work with properties file.
- ![](https://avatars.githubusercontent.com/u/1446536?s=24) [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) for coroutines (async).

## License
MusicProfileBot is under the MIT License. See the [LICENSE](LICENSE) for more information.
