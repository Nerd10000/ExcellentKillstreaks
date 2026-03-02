package dragon.me.excellentKillStreaks.hooks.papi;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderExpansion extends me.clip.placeholderapi.expansion.PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "exstreaks";
    }

    @Override
    public @NotNull String getAuthor() {
        return "DragonCraft64";
    }

    @Override
    public @NotNull String getVersion() {
        return "v1.0.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("streak")){
            return String.valueOf(ExcellentKillStreaks.getDataManager().getStreak(String.valueOf(player.getUniqueId())));
        }else if (params.startsWith("streak_")){
            String playerName = params.replace("streak_", "");

            if (Bukkit.getPlayer(playerName) == null) return  "0";

            Player p = Bukkit.getPlayer(playerName);

            int streak = ExcellentKillStreaks.getDataManager().getStreak(p.getUniqueId().toString());

            return  String.valueOf(streak);
        }else if (params.equalsIgnoreCase("best")){
            return  String.valueOf(ExcellentKillStreaks.getDataManager().getBest(player.getUniqueId().toString()));
        }else if (params.startsWith("best_")){
            String playerName = params.replace("best_", "");

            if (Bukkit.getPlayer(playerName) == null) return  "0";

            Player p = Bukkit.getPlayer(playerName);

            int streak = ExcellentKillStreaks.getDataManager().getBest(p.getUniqueId().toString());
            return String.valueOf(streak);
        }

        return "";
    }
}
