package dragon.me.excellentKillStreaks.commands;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import dragon.me.excellentKillStreaks.utils.ConfigProvider;
import dragon.me.excellentKillStreaks.utils.UpdateChecker;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class MainCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        CommandSender sender = commandSourceStack.getSender();
        if (args.length < 1){
            for (String s : ConfigProvider.HELP_COMMAND){
                sender.sendRichMessage(s);
            }
            return;
        }
        switch (args[0]){
            case "reload":


                sender.sendRichMessage(ConfigProvider.RELOAD_COMMAND);
                ExcellentKillStreaks.getConfigProvider().reload(ExcellentKillStreaks.getPlugin());
                ExcellentKillStreaks.getDataManager().load();
                ExcellentKillStreaks.getActionDataManager().load();

                break;

            case "update":
                sender.sendRichMessage(ConfigProvider.UPDATE_STARTED);

                Bukkit.getScheduler().runTaskAsynchronously(ExcellentKillStreaks.getPlugin(), () -> {
                    try {

                        String api = "https://api.modrinth.com/v2/project/%s/version"
                                .replace("%s", UpdateChecker.PLUGIN_ID);

                        HttpURLConnection connection = (HttpURLConnection) new URL(api).openConnection();
                        connection.setRequestProperty("User-Agent", "ExcellentKillStreaks-Updater");
                        connection.connect();

                        if (connection.getResponseCode() != 200) {
                            sender.sendRichMessage(ConfigProvider.UPDATE_FAILED);
                            return;
                        }

                        String json = new String(connection.getInputStream().readAllBytes());

                        JSONArray versions = new JSONArray(json);
                        JSONObject latest = versions.getJSONObject(0);

                        JSONArray files = latest.getJSONArray("files");
                        JSONObject file = files.getJSONObject(0);

                        String downloadUrl = file.getString("url");

                        File updateFolder = new File(Bukkit.getPluginsFolder(), "update");
                        updateFolder.mkdirs();

                        File output = new File(updateFolder, "ExcellentKillStreaks.jar");

                        try (InputStream in = new URL(downloadUrl).openStream();
                             FileOutputStream out = new FileOutputStream(output)) {

                            byte[] buffer = new byte[8192];
                            int len;

                            while ((len = in.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                        }

                        sender.sendRichMessage(ConfigProvider.UPDATE_SUCCEEDED);

                    } catch (Exception e) {
                        sender.sendRichMessage(ConfigProvider.UPDATE_FAILED);
                        e.printStackTrace();
                    }
                });

                break;

            case  "info":
                sender.sendRichMessage(ConfigProvider.INFO_COMMAND
                        .replace("%version%",ExcellentKillStreaks.getPlugin().getDescription().getVersion())
                        .replace("%author%",ExcellentKillStreaks.getPlugin().getDescription().getAuthors().get(0)));
                break;

            default:

        }
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return List.of("info","reload","update");
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender instanceof Player && sender.hasPermission("exstreaks.admin");
    }

    @Override
    public @Nullable String permission() {
        return "exstreaks.admin";
    }
}
