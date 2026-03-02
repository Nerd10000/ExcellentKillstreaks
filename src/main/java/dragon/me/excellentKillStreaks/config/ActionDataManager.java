package dragon.me.excellentKillStreaks.config;

import dragon.me.excellentKillStreaks.config.models.ActionModel;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class ActionDataManager {
    private Plugin plugin;
    private  final HashMap<Integer, ActionModel> modelHashMap = new HashMap<>();
    public ActionDataManager(Plugin plugin){
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }


    public HashMap<Integer, ActionModel> getModelHashMap() {
        return modelHashMap;
    }

    public void load(){
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("actions");

        for (String key : section.getKeys(false)){
            int requirement = plugin.getConfig().getInt("actions." + key + ".require_actions");
            List<String> commands = plugin.getConfig().getStringList("actions." + key + ".commands");
            ActionModel model = new ActionModel(key,requirement,commands);
            modelHashMap.put(requirement,model);
        }
    }
}
