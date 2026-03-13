package dragon.me.excellentKillStreaks.config.models;

import org.bukkit.plugin.Plugin;

public class AntiKillFarmSettings {
    private boolean enabled;
    private  int threshold;
    private int cooldown;
    public  AntiKillFarmSettings(Plugin plugin){

        if (plugin == null) return;

        enabled  = plugin.getConfig().getBoolean("anti_kill_farm.enabled");
        threshold = plugin.getConfig().getInt("anti_kill_farm.threshold");
        cooldown = plugin.getConfig().getInt("anti_kill_farm.cooldown");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getThreshold() {
        return threshold;
    }

    public int getCooldown() {
        return cooldown;
    }
}
