package tg.legaapk.bBAds;

public class MuteEntry {
    private final long endTime;

    public MuteEntry(long seconds) {
        this.endTime = System.currentTimeMillis() + seconds * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > endTime;
    }

    public long getRemainingSeconds() {
        return Math.max(0, (endTime - System.currentTimeMillis()) / 1000);
    }
}
