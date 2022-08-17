package ru.zalupa.command.commands;

import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help", "List of commands");
    }

    public void execute(CommandContext ctx) {
        for (Command cmd : Zalupa.getZalupa().getCommandManager().getRegisteredCommands().values()) {
            cmd.sendMessage("§8[§cZalupa§8] §fCommand §b." + cmd.getName() + ": \n" + cmd.getUsage());
        }

        val player = Zalupa.getSelfPlayer();
    }
}
