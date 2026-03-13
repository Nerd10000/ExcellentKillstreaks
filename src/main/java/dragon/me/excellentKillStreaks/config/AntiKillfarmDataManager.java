package dragon.me.excellentKillStreaks.config;

import dragon.me.excellentKillStreaks.config.models.AntiKillFarmSettings;
import it.unimi.dsi.fastutil.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AntiKillfarmDataManager {
    private Plugin plugin;
    private AntiKillFarmSettings antiKillFarmSettings;
    // The following Map is probably going to be replaced overtime as this is kinda slow.
    private HashMap<UUID, List<Triple<UUID,Long,Integer>>> dataMap = new HashMap<>();
    public AntiKillfarmDataManager(Plugin plugin){
        this.plugin = plugin;
        this.antiKillFarmSettings = new AntiKillFarmSettings(plugin);
    }

    public AntiKillFarmSettings getAntiKillFarmSettings() {
        return antiKillFarmSettings;
    }

    public HashMap<UUID, List<Triple<UUID,Long,Integer>>> getDataMap() {
        return dataMap;
    }
}
