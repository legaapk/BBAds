package tg.legaapk.bBAds;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class RegexManager {

    private final bBAds plugin;
    private List<String> regexList;

    public RegexManager(bBAds plugin) {
        this.plugin = plugin;
        loadRegex();
    }

    public void loadRegex() {
        File file = new File(plugin.getDataFolder(), "regex.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        regexList = config.getStringList("regex");
    }

    public boolean isAllowed(String message) {
        for (String regex : regexList) {
            if (Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(message).find()) {
                return false;
            }
        }
        return true;
    }
}
