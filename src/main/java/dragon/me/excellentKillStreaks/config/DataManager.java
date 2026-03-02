package dragon.me.excellentKillStreaks.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    public Map<UUID,Integer> dataMap = new HashMap<>();
    private Plugin plugin;
    public DataManager(Plugin plugin){
        this.plugin = plugin;
    }

    public Map<UUID, Integer> getDataMap() {
        return dataMap;
    }

    public void load() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("data");

        if (section == null) {
            // No stored data yet, nothing to load
            return;
        }

        for (String key : section.getKeys(false)) {
            int data = section.getInt(key + ".streak");
            dataMap.put(UUID.fromString(key), data);
        }
    }

    public int getStreak(String uuid){
        return plugin.getConfig().getInt("data." + uuid + ".streak",0);
    }

    public int getBest(String uuid){
        return plugin.getConfig().getInt("data." + uuid + ".best",0);
    }
    public void setStreak(String uuid, int value){
        plugin.getConfig().set("data." + uuid + ".streak",value);
        dataMap.put(UUID.fromString(uuid),value);

        try {
            plugin.getConfig().save(plugin.getDataFolder() + "/config.yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setBest(String uuid,int value){
        plugin.getConfig().set("data." + uuid + ".best",value);

        try {
            plugin.getConfig().save(plugin.getDataFolder() + "/config.yml");
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
