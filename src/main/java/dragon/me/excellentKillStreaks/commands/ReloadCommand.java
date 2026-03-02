package dragon.me.excellentKillStreaks.commands;

import dragon.me.excellentKillStreaks.ExcellentKillStreaks;
import dragon.me.excellentKillStreaks.utils.ConfigProvider;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class ReloadCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        CommandSender sender = commandSourceStack.getSender();

        sender.sendRichMessage(ConfigProvider.RELOAD_COMMAND);
        ExcellentKillStreaks.getConfigProvider().reload(ExcellentKillStreaks.getPlugin());

    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return BasicCommand.super.canUse(sender);
    }

    @Override
    public @Nullable String permission() {
        return "excellentkillstreaks.reload";
    }
}
