package dragon.me.excellentKillStreaks.listeners;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import dragon.me.excellentKillStreaks.config.DataManager;
import dragon.me.excellentKillStreaks.config.models.ActionModel;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class PlayerKillEntityListeners implements Listener {

    @EventHandler
    public void onEvent(PlayerDeathEvent e){

        Player killed = e.getEntity();

        if (killed.getKiller() == null) return;
        Player killer = killed.getKiller();

        int streak = ExcellentKillStreaks.getDataManager().getStreak(killer.getUniqueId().toString());

        ExcellentKillStreaks.getDataManager().setStreak(killer.getUniqueId().toString(),streak+1);

        if (ExcellentKillStreaks.getDataManager().getStreak(killer.getUniqueId().toString()) >
                ExcellentKillStreaks.getDataManager().getBest(killer.getUniqueId().toString())){

            ExcellentKillStreaks.getDataManager().setBest(killer.getUniqueId().toString(),
                    ExcellentKillStreaks.getDataManager().getStreak(killer.getUniqueId().toString()));

        }

        ActionModel actionModel = null;
        for (ActionModel model : ExcellentKillStreaks.getActionDataManager().getModelHashMap().values()){
            if (model.requirement == streak){
                actionModel = model;
                break;
            }
        }
        if (actionModel == null){
            return;
        }else {
            for ( String command : actionModel.commands){
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),command
                        .replace("%player%",killer.getName())
                        .replace("%killed%",killed.getName()));
                Bukkit.getLogger().info(command
                        .replace("%player%",killer.getName())
                        .replace("%killed%",killed.getName()));
            }
        }


    }
}
