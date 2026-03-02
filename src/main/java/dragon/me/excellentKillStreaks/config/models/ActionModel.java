package dragon.me.excellentKillStreaks.config.models;

import dragon.me.excellentKillStreaks.config.ActionDataManager;

import java.util.List;

public class ActionModel {

    public final String name;
    public final int requirement;
    public final List<String> commands;

    public ActionModel(String name, int requirement, List<String> commands) {
        this.name = name;
        this.requirement = requirement;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public int getRequirement() {
        return requirement;
    }

    public List<String> getCommands() {
        return commands;
    }
}
