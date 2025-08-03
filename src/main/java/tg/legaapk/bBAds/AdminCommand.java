package tg.legaapk.bBAds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminCommand implements CommandExecutor {

    private final bBAds plugin;

    public AdminCommand(bBAds plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("bossbarads.admin")) {
            sender.sendMessage("§cYou don't have permission to execute this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("Usage: /bossbarad <on|off|stop>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on":
                plugin.getBossBarManager().setServerActive(true);
                sender.sendMessage("Automatic server ads enabled.");
                break;
            case "off":
                plugin.getBossBarManager().setServerActive(false);
                sender.sendMessage("Automatic server ads disabled.");
                break;
            case "stop":
                plugin.getBossBarManager().stop();
                sender.sendMessage("All bossbars stopped immediately.");
                break;
            case "reload":
                plugin.reloadConfig();
                plugin.getBossBarManager().reload();
                sender.sendMessage("Configuration reloaded.");
                break;
            default:
                sender.sendMessage("Usage: /bossbarad <on|off|stop|reload>");
        }

        return true;
    }
}
