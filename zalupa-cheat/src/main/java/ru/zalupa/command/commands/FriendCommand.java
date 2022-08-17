package ru.zalupa.command.commands;

import ru.zalupa.Zalupa;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;
import ru.zalupa.command.parameter.Parameter;
import ru.zalupa.command.type.TypeString;

public class FriendCommand extends Command {

    public FriendCommand() {
        super("friends", "Friend system", "f");
        subCommand(new Command("add", "Add new friend") {
            {
                parameter(new Parameter<>("Nickname", new TypeString(2, 16)));
            }
            public void execute(CommandContext ctx) {
                Zalupa.getZalupa().getFriendManager().addFriend(ctx.getArg(0));
                ctx.sendMessage("§8[§cZalupa§8] §fNew friend §7- §a%s", ctx.<String>getArg(0));
            }
        });
        subCommand(new Command("remove", "Remove friend") {
            {
                parameter(new Parameter<>("Nickname", new TypeString(2, 16)));
            }
            public void execute(CommandContext ctx) {
                Zalupa.getZalupa().getFriendManager().removeFriend(ctx.getArg(0));
                ctx.sendMessage("§8[§cZalupa§8] §fRemoved friend §7- §a%s", ctx.<String>getArg(0));
            }
        });
        subCommand(new Command("list", "List of friend") {
            public void execute(CommandContext ctx) {
                ctx.sendMessage("§8[§cZalupa§8] §fFriends §7- §a%s",
                        String.join("§7, §a", Zalupa.getZalupa().getFriendManager().getFriends()));
            }
        });
        subCommand(new Command("clear", "Clear friend") {
            public void execute(CommandContext ctx) {
                Zalupa.getZalupa().getFriendManager().getFriends().clear();
                Zalupa.getZalupa().getFriendManager().save();
                ctx.sendMessage("§8[§cZalupa§8] §fFriends cleard ");
            }
        });
        subCommand(new Command("clear", "Clear friends") {
            public void execute(CommandContext ctx) {
                Zalupa.getZalupa().getFriendManager().clearFriends();
                ctx.sendMessage("§8[§cZalupa§8] §fCleared friends");
            }
        });
    }

    public void execute(CommandContext ctx) {
        ctx.sendMessage(getUsage());
    }

}
