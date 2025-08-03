package tg.legaapk.bBAds;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    private static final Path path = bBAds.getInstance().getDataFolder().toPath().resolve("logs.txt");

    public static void log(String msg) {
        try {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Files.write(path, (time + " " + msg + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
