package dragon.me.excellentKillStreaks;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import dragon.me.excellentKillStreaks.commands.BestStreakCommand;
import dragon.me.excellentKillStreaks.commands.KillstreakCommand;
import dragon.me.excellentKillStreaks.commands.ReloadCommand;
import dragon.me.excellentKillStreaks.config.ActionDataManager;
import dragon.me.excellentKillStreaks.config.DataManager;
import dragon.me.excellentKillStreaks.hooks.papi.PlaceholderExpansion;
import dragon.me.excellentKillStreaks.listeners.PlayerDeathListener;
import dragon.me.excellentKillStreaks.listeners.PlayerKillEntityListeners;
import dragon.me.excellentKillStreaks.utils.ConfigProvider;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExcellentKillStreaks extends JavaPlugin {
    private static Plugin plugin;
    private  static DataManager dataManager;
    private  static ActionDataManager actionDataManager;
    private static ConfigProvider configProvider;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        plugin = this;
        dataManager = new DataManager(this);
        dataManager.load();
        actionDataManager  = new ActionDataManager(this);
        actionDataManager.load();

        configProvider = new ConfigProvider(this);
        if (Bukkit.getPluginManager().isPluginEnabled("ExcellentKillStreaks")){
            new PlaceholderExpansion().register();
        }

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerKillEntityListeners(),this);

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register("exreload",new ReloadCommand());
            commands.registrar().register("streak", new KillstreakCommand());
            commands.registrar().register("beststreak",new BestStreakCommand());
        });


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static DataManager getDataManager() {
        return dataManager;
    }

    public static ActionDataManager getActionDataManager() {
        return actionDataManager;
    }

    public static ConfigProvider getConfigProvider() {
        return configProvider;
    }
}
