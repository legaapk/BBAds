package tg.legaapk.bBAds;

import org.bukkit.boss.BarColor;

public class PlayerAd {
    private final String sender;
    private final String message;
    private final BarColor color;
    private final int duration;

    public PlayerAd(String sender, String message, BarColor color, int duration) {
        this.sender = sender;
        this.message = message;
        this.color = color;
        this.duration = duration;
    }

    public String getSender() { return sender; }
    public String getMessage() { return message; }
    public BarColor getColor() { return color; }
    public int getDuration() { return duration; }
}
