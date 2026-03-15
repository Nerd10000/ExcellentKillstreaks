package dragon.me.excellentKillStreaks.utils;

import org.bukkit.plugin.Plugin;

import java.util.List;

public class ConfigProvider {

    public  static String PREFIX;

    public static String SELF_COMMAND;
    public static String OTHER_COMMAND;
    public static String RELOAD_COMMAND;
    public  static  String NO_SUCH_PLAYER;
    public static String SELF_BEST_COMMAND;
    public static String OTHER_BEST_COMMAND;
    public static String ANTI_KILLFARM_ALERT;
    public static String UPDATE_STARTED;
    public static String UPDATE_FAILED;
    public static String UPDATE_SUCCEEDED;
    public  static List<String> HELP_COMMAND;
    public static String INFO_COMMAND;

    public ConfigProvider(Plugin plugin){
        PREFIX = plugin.getConfig().getString("prefix");
        SELF_COMMAND = prefix(plugin.getConfig().getString("killstreak_self_command"));
        OTHER_COMMAND = prefix(plugin.getConfig().getString("killstreak_other_command"));
        RELOAD_COMMAND = prefix(plugin.getConfig().getString("reload_command"));
        NO_SUCH_PLAYER = prefix(plugin.getConfig().getString("no_such_player"));
        SELF_BEST_COMMAND = prefix(plugin.getConfig().getString("best_streak_self_command"));
        OTHER_BEST_COMMAND = prefix(plugin.getConfig().getString("best_streak_other_command"));
        ANTI_KILLFARM_ALERT = prefix(plugin.getConfig().getString("anti_kill_farm_message"));
        UPDATE_STARTED = prefix(plugin.getConfig().getString("update_started"));
        UPDATE_SUCCEEDED = prefix(plugin.getConfig().getString("update_ended_without_errors"));
        UPDATE_FAILED = prefix(plugin.getConfig().getString("update_ended_with_errors"));
        HELP_COMMAND = plugin.getConfig().getStringList("help_command");
        INFO_COMMAND = prefix(plugin.getConfig().getString("info_command"));
    }
    public void reload(Plugin plugin){
        PREFIX = plugin.getConfig().getString("prefix");
        SELF_COMMAND = prefix(plugin.getConfig().getString("killstreak_self_command"));
        OTHER_COMMAND = prefix(plugin.getConfig().getString("killstreak_other_command"));
        RELOAD_COMMAND = prefix(plugin.getConfig().getString("reload_command"));
        NO_SUCH_PLAYER = prefix(plugin.getConfig().getString("no_such_player"));
        SELF_BEST_COMMAND = prefix(plugin.getConfig().getString("best_streak_self_command"));
        OTHER_BEST_COMMAND = prefix(plugin.getConfig().getString("best_streak_other_command"));
        ANTI_KILLFARM_ALERT = prefix(plugin.getConfig().getString("anti_kill_farm_message"));
        UPDATE_STARTED = prefix(plugin.getConfig().getString("update_started"));
        UPDATE_SUCCEEDED = prefix(plugin.getConfig().getString("update_ended_without_errors"));
        UPDATE_FAILED = prefix(plugin.getConfig().getString("update_ended_with_errors"));
        HELP_COMMAND = plugin.getConfig().getStringList("help_command");
        INFO_COMMAND = prefix(plugin.getConfig().getString("info_command"));
        plugin.reloadConfig();
    }

    public  String prefix(String text){
        return  text.replace("%prefix%",PREFIX);
    }
}
