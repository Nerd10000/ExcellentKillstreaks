package dragon.me.excellentKillStreaks.commands;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import dragon.me.excellentKillStreaks.utils.ConfigProvider;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class BestStreakCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {

        Player p = (Player) commandSourceStack.getSender();

        if (args.length < 1){
            p.sendRichMessage(ConfigProvider.SELF_BEST_COMMAND
                    .replace("%best%",String.valueOf(ExcellentKillStreaks.getDataManager().getBest(p.getUniqueId().toString()))));

        }else  {
            Player other = Bukkit.getPlayer(args[0]);
            if (other == null){
                p.sendRichMessage(ConfigProvider.NO_SUCH_PLAYER);
                return;
            }
            other.sendRichMessage(ConfigProvider.OTHER_BEST_COMMAND
                    .replace("%player%",args[0])
                    .replace("%best%",String.valueOf(ExcellentKillStreaks.getDataManager().getBest(other.getUniqueId().toString()))));
        }

    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return Bukkit.getOnlinePlayers().stream().map(player -> player.getName()).toList();
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender instanceof Player;
    }
}
