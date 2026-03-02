package dragon.me.excellentKillStreaks.listeners;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onEvent(PlayerDeathEvent e){
        if (e.getEntity().getKiller() instanceof  Player p){
            ExcellentKillStreaks.getDataManager().setStreak(e.getEntity().getUniqueId().toString(),0);



            for (String command : ExcellentKillStreaks.getPlugin().getConfig().getStringList("lose_streak_commands")){

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command
                        .replace("%player%",e.getEntity().getName())
                        .replace("%killer%", p.getName()));
            }
        }
    }
}
