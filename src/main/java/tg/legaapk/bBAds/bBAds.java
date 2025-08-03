package tg.legaapk.bBAds;

import org.bukkit.plugin.java.JavaPlugin;

public class bBAds extends JavaPlugin {

    private static bBAds instance;
    private BossBarManager bossBarManager;
    private RegexManager regexManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        saveResource("regex.yml", false);
        regexManager = new RegexManager(this);
        bossBarManager = new BossBarManager(this);
        getCommand("bb").setExecutor(new BbCommand(this));
        getCommand("bossbarad").setExecutor(new AdminCommand(this));
        bossBarManager.start();
    }

    @Override
    public void onDisable() {
        bossBarManager.stop();
    }

    public static bBAds getInstance() {
        return instance;
    }

    public BossBarManager getBossBarManager() {
        return bossBarManager;
    }

    public RegexManager getRegexManager() {
        return regexManager;
    }
}
