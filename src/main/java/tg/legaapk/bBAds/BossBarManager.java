package tg.legaapk.bBAds;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BossBarManager {

    private final bBAds plugin;
    private final Queue<PlayerAd> playerQueue = new LinkedList<>();
    private final Map<String, MuteEntry> mutedPlayers = new HashMap<>();
    private final List<String> serverAds;
    private final String barId;
    private boolean alternate;
    private boolean serverActive = true;
    private int serverIndex = 0;
    private boolean showing = false;
    private String playerAdFormat;

    public BossBarManager(bBAds plugin) {
        this.plugin = plugin;
        this.barId = plugin.getConfig().getString("bossbar_id", "ads");
        this.serverAds = plugin.getConfig().getStringList("server_ads");
        this.alternate = plugin.getConfig().getBoolean("alternate_ads", true);
        this.playerAdFormat = plugin.getConfig().getString("player_ad_format", "§7§o%name%§r: %message%");
        loadMutes();
    }

    public void reload() {
        this.playerAdFormat = plugin.getConfig().getString("player_ad_format", "§7§o%name%§r: %message%");
    }


    public void start() {
        new BukkitRunnable() {
            public void run() {
                checkExpiredMutes();

                if (showing) return;
                if (!playerQueue.isEmpty()) {
                    showPlayerAd(playerQueue.poll());
                } else if (alternate && serverActive) {
                    showServerAd();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public void stop() {
        Bukkit.getBossBars().forEachRemaining(BossBar::removeAll);
    }

    private void showPlayerAd(PlayerAd ad) {
        showing = true;
        BossBar bar = Bukkit.createBossBar(ad.getMessage(), ad.getColor(), BarStyle.SOLID); // Используем отформатированное сообщение
        Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
        bar.setVisible(true);
        if (plugin.getConfig().getBoolean("enable_logging"))
            LogUtil.log(ad.getMessage()); // Логируем отформатированное сообщение
        new BukkitRunnable() {
            public void run() {
                bar.removeAll();
                showing = false;
            }
        }.runTaskLater(plugin, ad.getDuration() * 20L);
    }

    private void showServerAd() {
        if (serverAds.isEmpty()) return;
        showing = true;
        String msg = org.bukkit.ChatColor.translateAlternateColorCodes('&', serverAds.get(serverIndex));
        serverIndex = (serverIndex + 1) % serverAds.size();
        BossBar bar = Bukkit.createBossBar(msg, BarColor.PURPLE, BarStyle.SOLID);
        Bukkit.getOnlinePlayers().forEach(bar::addPlayer);
        bar.setVisible(true);
        new BukkitRunnable() {
            public void run() {
                bar.removeAll();
                showing = false;
            }
        }.runTaskLater(plugin, getServerAdDuration() * 20L);
    }

    public void enqueueAd(PlayerAd ad) {
        playerQueue.offer(ad);
    }

    public void setServerActive(boolean b) {
        this.serverActive = b;
    }

    public int getQueuePosition(String player) {
        int i = 1;
        for (PlayerAd ad : playerQueue) {
            if (ad.getSender().equalsIgnoreCase(player)) return i;
            i++;
        }
        return -1;
    }

    // Mute system
    public boolean isMuted(String name) {
        MuteEntry m = mutedPlayers.get(name.toLowerCase());
        return m != null && !m.isExpired();
    }

    public void mute(String name, long seconds) {
        mutedPlayers.put(name.toLowerCase(), new MuteEntry(seconds));
        saveMutes();
    }

    public void unmute(String name) {
        mutedPlayers.remove(name.toLowerCase());
        saveMutes();
    }

    private void checkExpiredMutes() {
        mutedPlayers.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    private void loadMutes() {
        mutedPlayers.clear();
        if (plugin.getConfig().getConfigurationSection("muted_players") != null) {
            plugin.getConfig().getConfigurationSection("muted_players").getValues(false).forEach((name, value) -> {
                mutedPlayers.put(name.toLowerCase(), new MuteEntry((Integer) value));
            });
        }
    }

    private void saveMutes() {
        Map<String, Long> result = new HashMap<>();
        mutedPlayers.forEach((name, mute) -> result.put(name, mute.getRemainingSeconds()));
        plugin.getConfig().set("muted_players", result);
        plugin.saveConfig();
    }

    private int getServerAdDuration() {
        int min = plugin.getConfig().getInt("server_ads_interval.min", 30);
        int max = plugin.getConfig().getInt("server_ads_interval.max", 60);
        return min + new Random().nextInt(max - min + 1);
    }
}
