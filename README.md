## bBAds - BossBar Advertisement Plugin for Minecraft

bBAds is a powerful and configurable Minecraft plugin that allows server owners to display advertisements, announcements, and custom messages via the BossBar. This plugin provides a flexible way to communicate with players, promote server features, and even allow players to create their own advertisements (with appropriate permissions and restrictions).

### Features

•   Server Advertisements:
    *   Display a rotating list of server-defined messages in the BossBar.
    *   Configurable interval between advertisements.
    *   Color code support for visually appealing messages.
•   Player Advertisements:
    *   Allows players to create and display their own custom messages in the BossBar (requires permission).
    *   Configurable duration for player advertisements.
    *   BossBar color selection for player advertisements.
    *   Message color selection for player advertisements
    *   Customizable message format (e.g., [PlayerName]: Message).
    *   Queue system for player advertisements.
•   Admin Control:
    *   Enable/disable server advertisements.
    *   Stop all active BossBars immediately.
    *   Mute players from sending advertisements (with configurable duration).
    *   Reload configuration without server restart.
•   Permissions System:
    *   Granular control over who can use the plugin and for how long they can display their advertisements.
•   Mute System:
    *   Prevent players from abusing the system by muting them.
    *   Configurable mute durations.
•   Regex Filtering:
    *   Prevent unwanted or inappropriate content with a configurable list of regular expressions and words.
•   Logging:
    *   Log all displayed advertisements for moderation and tracking purposes.
•   Configuration:
    *   Highly configurable through config.yml and regex.yml.

### Commands

•   /bb <duration_seconds> <color_bb> <color_mssg> <message> -  Send a BossBar advertisement (requires bossbarads.use permission).
    *   <duration_seconds>: The duration of the advertisement in seconds.
    *   <color_bb>:  The color of the BossBar (e.g., BLUE, RED, GREEN).
    *   <color_mssg>:  The color of the message text (e.g., AQUA, YELLOW).
    *   <message>: The message to display.
•   /bb mute <player> [seconds] - Mute a player from sending advertisements (requires bossbarads.admin permission). Defaults to 3600 seconds if no seconds are specified.
•   /bb unmute <player> - Unmute a player (requires bossbarads.admin permission).
•   /bossbarad <on|off|stop|reload> - Admin commands for managing server advertisements (requires bossbarads.admin permission).
    *   on: Enable automatic server advertisements.
    *   off: Disable automatic server advertisements.
    *   stop: Stop all BossBars immediately.
    *   reload: Reloads the configuration files (config.yml, regex.yml)

### Permissions

•   bossbarads.use: Allows a player to use the /bb command to send advertisements.
•   bossbarads.kd.15: Allows a player to send advertisements for up to 15 seconds.
•   bossbarads.kd.30: Allows a player to send advertisements for up to 30 seconds.
•   bossbarads.kd.60: Allows a player to send advertisements for up to 60 seconds.
•   bossbarads.kd.120: Allows a player to send advertisements for up to 120 seconds.
•   bossbarads.kd.unlimited: Allows a player to send advertisements for unlimited duration.
•   bossbarads.admin: Allows access to admin commands (/bossbarad, /bb mute, /bb unmute).

### Configuration

•   config.yml:  Configure general plugin settings, server advertisements, and intervals.
    enable_logging: true # Enable logging of advertisements
    alternate_ads: true  # Alternate between player and server ads
    bossbar_id: "ads"      # Identifier for the bossbar

    player_ads_interval:  # Minimum and maximum time between player ads
      min: 15
      max: 60

    server_ads_interval:  # Minimum and maximum time between server ads
      min: 30
      max: 60

    server_ads:           # List of server advertisements
      - "&aWelcome to our server!"
      - "&eJoin our Discord for rewards!"

    muted_players:        # List of muted players
      player1: 0           # Player1 is muted for 0 seconds (permanently until unmuted)

    player_ad_format: "§7§o%name%§r: %message%" # Format for player advertisements


    *  regex.yml: Define regular expressions or words to filter in player messages.
        regex:
      - "badword1"
      - "badword2"
      - ".*website\\.com.*"

### Installation

1. Download the latest version of the bBAds plugin.
2. Place the .jar file in the plugins folder of your Minecraft server.
3. Start or restart the server.
4. Edit the config.yml and regex.yml files in the plugins/bBAds folder to customize the plugin to your liking.

▌Dependencies

•  Spigot or Paper (or a compatible Bukkit/Spigot server implementation)

▌License

This project is licensed under the [MIT License](LICENSE).

▌Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues.

