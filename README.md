## bBAds - Making Some Noise in the BossBar! 😎

Basically, this is a Minecraft plugin so your players don't get bored! It lets you display all sorts of cool stuff in the BossBar, so everyone can see it.

### What's in the Box:

*   Server Ads: The plugin automatically rotates pre-written announcements. Write something awesome in config.yml, and everyone will see it!
*   Player Ads: Let players show off! If they've got the permission, their message will appear in the BossBar. Control the duration so they don't abuse it.
*   Admin Control: Enable/disable server ads, silence anyone who's spamming, and reload the config without restarting the server.
*   Mutes: Silence those who write nonsense!
*   Curse Filter: Don't let bad words through! Write them in regex.yml.
*   Logs: Record all messages to a file, so you can figure out who wrote what later.

### Commands (for Players):

*   /bb <time> <bossbar_color> <text_color> <message> - Sends your message to the BossBar!
    *   <time>: How many seconds the message will be displayed.
    *   <bossbar_color>: The BossBar color (e.g., BLUE, RED, GREEN).
    *   <text_color>: The color of your message (e.g., AQUA, YELLOW).
    *   <message>: The text you want to show.

    Example: /bb 10 RED YELLOW Check out my shop!

### Commands (for Admins):

*   /bb mute <player> [time] - Silences a player so they can't send messages. If you don't specify <time>, it will silence them forever (until you unmute them).
*   /bb unmute <player> - Unmutes a player.
*   /bossbarad <on|off|stop|reload> - Server Management!
    *   on: Enables automatic server ads.
    *   off: Disables server ads.
    *   stop: Stops all BossBars right now!
    *   reload: Reloads settings from the config.yml and regex.yml files.

### Permissions:

*   bossbarads.use: Allows a player to use the /bb command.
*   bossbarads.kd.15: Allows sending messages for 15 seconds.
*   bossbarads.kd.30: Allows sending messages for 30 seconds.
*   bossbarads.kd.60: Allows sending messages for 60 seconds.
*   bossbarads.kd.120: Allows sending messages for 120 seconds.
*   bossbarads.kd.unlimited: Allows sending messages with no time limit.
*   bossbarads.admin: Gives access to admin commands.

### Configuration (config.yml):
enable_logging: true # Write logs or not
alternate_ads: true  # Alternate between player and server ads
bossbar_id: "ads"      # ID of the bossbar (better not to touch)

player_ads_interval:  # Time between player ads (in seconds)
  min: 15
  max: 60

server_ads_interval:  # Time between server ads (in seconds)
  min: 30
  max: 60

server_ads:           # Write your advertising messages here
  - "&aCome join us, it's fun here!"
  - "&eSubscribe to our Discord!"

muted_players:        # List of those who are silenced
  player1: 0           # Player1 is silenced forever (until unmuted)

player_ad_format: "§7§o%name%§r: %message%" # How player ads look.
                                           # %name% - player's name
                                           # %message% - player's message

### Curse Filter (regex.yml):
regex: # List of words and phrases that cannot be used
  - "bad_word1"
  - "bad_word2"
  - ".*website\\.com.*" # Ban websites

### Installation:

1.  Download the .jar file of the plugin.
2.  Drop it into the plugins folder on your Minecraft server.
3.  Start the server.
4.  Customize the config.yml and regex.yml files to your liking.

### Dependencies:

*   Spigot or Paper (or another server that supports Bukkit/Spigot plugins)

### License:

This plugin is distributed under the Apache license version 2.0; you can use this file only in accordance with the License.

### How to Contribute:

Suggest your ideas!
