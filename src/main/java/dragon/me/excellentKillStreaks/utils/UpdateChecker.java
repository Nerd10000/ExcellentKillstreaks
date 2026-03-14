package dragon.me.excellentKillStreaks.utils;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

    private static final String PLUGIN_ID = "wm4KGLZN";
    private static final String API = "https://api.modrinth.com/v2/project/%s/version";

    public static void check() {

        Bukkit.getScheduler().runTaskAsynchronously(ExcellentKillStreaks.getPlugin(), () -> {

            try {

                URL url = new URL(String.format(API, PLUGIN_ID));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("User-Agent", "ExcellentKillStreaks Update Checker");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                StringBuilder json = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }

                reader.close();

                /*
                 Modrinth returns an array of versions.
                 The first entry is the newest.
                */

                String response = json.toString();

                // simple parsing (no JSON lib needed)
                String latestVersion = response.split("\"version_number\":\"")[1].split("\"")[0];

                PluginDescriptionFile description =
                        ExcellentKillStreaks.getPlugin().getDescription();

                String currentVersion = description.getVersion();

                if (!currentVersion.equalsIgnoreCase(latestVersion)) {

                    Bukkit.getScheduler().runTask(ExcellentKillStreaks.getPlugin(), () -> {

                        Bukkit.getConsoleSender().sendMessage(
                                "§6[ExcellentKillStreaks] §eA new version is available!"
                        );

                        Bukkit.getConsoleSender().sendMessage(
                                "§7Current: §c" + currentVersion +
                                        " §7Latest: §a" + latestVersion
                        );

                        Bukkit.getConsoleSender().sendMessage(
                                "§7Download: §bhttps://modrinth.com/plugin/excellentkillstreaks"
                        );

                    });

                }

            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(
                        "§c[ExcellentKillStreaks] Update check failed."
                );
            }

        });

    }
}