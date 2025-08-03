package tg.legaapk.bBAds;

import org.bukkit.boss.BarColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class BbCommand implements CommandExecutor {

    private final bBAds plugin;

    public BbCommand(bBAds plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("mute") && sender.hasPermission("bossbarads.admin")) {
            if (args.length < 2) {
                sender.sendMessage("Usage: /bb mute <player> [seconds]");
                return true;
            }
            long time = args.length >= 3 ? Long.parseLong(args[2]) : 3600;
            plugin.getBossBarManager().mute(args[1], time);
            sender.sendMessage("Player " + args[1] + " muted for " + time + " seconds.");
            return true;
        }

        if (args.length >= 1 && args[0].equalsIgnoreCase("unmute") && sender.hasPermission("bossbarads.admin")) {
            if (args.length < 2) {
                sender.sendMessage("Usage: /bb unmute <player>");
                return true;
            }
            plugin.getBossBarManager().unmute(args[1]);
            sender.sendMessage("Player " + args[1] + " unmuted.");
            return true;
        }

        if (!p.hasPermission("bossbarads.use")) {
            p.sendMessage("§cYou don't have permission to use this command.");
            return true;
        }

        if (plugin.getBossBarManager().isMuted(p.getName())) {
            p.sendMessage("§cYou are muted from sending ads.");
            return true;
        }

        if (args.length < 4) {
            p.sendMessage("§cUsage: /bb <duration_seconds> <color_bb> <color_mssg> <message>");
            return true;
        }

        int time;
        try {
            time = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            p.sendMessage("§cInvalid duration. Please enter a number.");
            return true;
        }

        if (!PermissionUtil.canUseTime(p, time)) {
            p.sendMessage("§cYou don't have permission to send ads for that duration.");
            return true;
        }

        BarColor color;
        try {
            color = BarColor.valueOf(args[1].toUpperCase());
        } catch (IllegalArgumentException e) {
            p.sendMessage("§cInvalid BossBar color. Valid colors: BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW");
            return true;
        }

        ChatColor msgColor;
        try {
            msgColor = ChatColor.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            p.sendMessage("§cInvalid message color. Valid colors: BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE");
            return true;
        }

        String msg = String.join(" ", args).substring(args[0].length() + args[1].length() + args[2].length() + 3);

        if (!plugin.getRegexManager().isAllowed(msg)) {
            p.sendMessage("§cYour ad contains forbidden words or patterns.");
            return true;
        } else {
            String formattedMessage = plugin.getConfig().getString("player_ad_format", "§7§o%name%§r: %message%")
                    .replace("%name%", p.getName())
                    .replace("%message%", msgColor + msg);

            PlayerAd ad = new PlayerAd(p.getName(), formattedMessage, color, time);
            plugin.getBossBarManager().enqueueAd(ad);

            int pos = plugin.getBossBarManager().getQueuePosition(p.getName());
            if (pos > 1) {
                p.sendMessage("§eYour ad is queued. Position: §f#" + pos);
            } else {
                p.sendMessage("§aYour ad will be shown soon.");
            }

            return true;
        }

    }
}
