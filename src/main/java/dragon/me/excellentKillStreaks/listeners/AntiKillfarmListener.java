package dragon.me.excellentKillStreaks.listeners;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import dragon.me.excellentKillStreaks.utils.ConfigProvider;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;


public class AntiKillfarmListener implements Listener {

    @EventHandler
    public void onEvent(PlayerDeathEvent e) {

        if (e.getEntity().getKiller() == null) return;

        if (!ExcellentKillStreaks.getKillfarmDataManager()
                .getAntiKillFarmSettings().isEnabled()) return;

        Player killer = e.getEntity().getKiller();
        UUID killerId = killer.getUniqueId();
        UUID victimId = e.getEntity().getUniqueId();

        long now = System.currentTimeMillis();

        HashMap<UUID, List<Triple<UUID, Long, Integer>>> data =
                ExcellentKillStreaks.getKillfarmDataManager().getDataMap();

        List<Triple<UUID, Long, Integer>> victims =
                data.computeIfAbsent(killerId, k -> new ArrayList<>());

        Optional<Triple<UUID, Long, Integer>> tripleOptional = victims.stream()
                .filter(t -> t.getLeft().equals(victimId))
                .findFirst();

        if (tripleOptional.isPresent()) {

            Triple<UUID, Long, Integer> value = tripleOptional.get();

            long lastKillTime = value.getMiddle();
            int killCount = value.getRight();

            long cooldownMillis =
                    ExcellentKillStreaks.getKillfarmDataManager()
                            .getAntiKillFarmSettings().getCooldown() * 1000L;

            if ((now - lastKillTime) < cooldownMillis) {

                killCount++;

                if (killCount >= ExcellentKillStreaks.getKillfarmDataManager()
                        .getAntiKillFarmSettings().getThreshold()) {

                    killer.sendRichMessage(ConfigProvider.ANTI_KILLFARM_ALERT
                            .replace("%player%", e.getEntity().getName()));
                    ExcellentKillStreaks.getDataManager().setStreak(killerId.toString(),
                            ExcellentKillStreaks.getDataManager().getStreak(killerId.toString())-1);
                    return;
                }

                victims.remove(value);
                victims.add(Triple.of(victimId, now, killCount));

            } else {

                victims.remove(value);
                victims.add(Triple.of(victimId, now, 1));
            }

        } else {

            victims.add(Triple.of(victimId, now, 1));
        }

        data.put(killerId, victims);
    }
}