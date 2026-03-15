package dragon.me.excellentKillStreaks.utils;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

    public static final String PLUGIN_ID = "wm4KGLZN";
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


                String response = json.toString();


                String latestVersion = response.split("\"version_number\":\"")[1].split("\"")[0];

                PluginDescriptionFile description =
                        ExcellentKillStreaks.getPlugin().getDescription();

                String currentVersion = description.getVersion();

                if (!currentVersion.equalsIgnoreCase(latestVersion)) {

                    Bukkit.getScheduler().runTask(ExcellentKillStreaks.getPlugin(), () -> {

                        ExcellentKillStreaks.getPlugin().getLogger().warning("Hey, we noticed you are using an outdated version of ExcellentKillstreaks!");
                        ExcellentKillStreaks.getPlugin().getLogger().warning("Your version '" + currentVersion +"' while the latest version '" + latestVersion + "', we recommend you to update for full compatibility and for new features.");
                        ExcellentKillStreaks.getPlugin().getLogger().warning("Download at https://modrinth.com/plugin/excellentkillstreaks");
                    });

                }

            } catch (Exception e) {

                ExcellentKillStreaks.getPlugin().getLogger().severe("[ExcellentKillStreaks] Update check failed.");
                e.printStackTrace();
            }

        });

    }
}