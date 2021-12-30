# daem0ns

A simple Minecraft Server plugin that has some small little tools to help 

**Supports 1.17 - 1.18.1**

## Commands

* `/muteall` Mutes all players in the server, even those that have just joined
* `/clearchat` Clears chat with specified amount of lines
* `/hidechat` Sends a chat message without any player tag, supports colors and formatting codes

## Permissions

* `daem0ns.muteall` Gives player to toggle the mute state of the server
  * `daem0ns.muteall.bypass` Gives player to bypass the mute state
* `daem0ns.clearchat` Gives player to clear the chat
* `daem0ns.hide` Gives player to send hidden chat messages
* `daem0ns.perfreport` Gives player the ability to receive messages if the server is not running at a specified TPS
* `daem0ns.*` Gives player all `daem0ns` permissions/commands

## Default Config

```yml
###################################################
#         __                     ____             #
#    ____/ /___ ____  ____ ___  / __ \____  _____ #
#   / __  / __ `/ _ \/ __ `__ \/ / / / __ \/ ___/ #
#  / /_/ / /_/ /  __/ / / / / / /_/ / / / (__  )  #
#  \__,_/\__,_/\___/_/ /_/ /_/\____/_/ /_/____/   #
###################################################

# How many new lines will be created when you run /clearchat
# Default: 100
clearchat-line-inserts: 100

# TPS checker, each value should never be higher than 20, as a server TPS will only go as high as 20
# Console messages are the normal messages telling the console that the server is running normally. This helps cut down
# on console spam if left false. If the server is unstable it will still broadcast a message.
tps:
  1m: 18
  5m: 19
  15m: 19
  console-messages: false
```

## Compiling from source

### Requirements

* Gradle
* Java 16

### Steps

1. `git clone https://github.com/garrettsummerfi3ld/daem0ns.git`
2. `./gradlew build`