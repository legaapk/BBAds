package tg.legaapk.bBAds;

import org.bukkit.entity.Player;

public class PermissionUtil {
    public static boolean canUseTime(Player p, int time) {
        if (time <= 15 && p.hasPermission("bossbarads.kd.15")) return true;
        if (time <= 30 && p.hasPermission("bossbarads.kd.30")) return true;
        if (time <= 60 && p.hasPermission("bossbarads.kd.60")) return true;
        if (time <= 120 && p.hasPermission("bossbarads.kd.120")) return true;
        if (p.hasPermission("bossbarads.kd.unlimited")) return true;
        return false;
    }
}
