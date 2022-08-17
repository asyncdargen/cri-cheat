package ru.zalupa.command.commands;

import com.google.common.collect.Lists;
import dev.xdark.clientapi.event.block.BlockLeftClick;
import dev.xdark.clientapi.util.BusUtil;
import lombok.val;
import ru.zalupa.Zalupa;
import ru.zalupa.cheat.Cheat;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;
import ru.zalupa.command.parameter.Parameter;
import ru.zalupa.command.type.TypeString;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NukerFilterCommand extends Command {

    private boolean clickMode;

    public NukerFilterCommand() {
        super("nukerfilter", "Nuker Filter", "nf");

        BlockLeftClick.BUS.register(event -> {
            if (!clickMode && Zalupa.isOnline() && !Zalupa.isOpenScreen())
                return;

            val pos = event.getPosition();
            add(Zalupa.getSelfPlayer().getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ()).getId());
        });

        subCommand(new Command("add", "Add ids to filter") {
            {
                parameter(new Parameter<>("ids", new TypeString()));
            }
            public void execute(CommandContext ctx) {
                String arg = ctx.getArg(0);

                if (arg.equalsIgnoreCase("c") || arg.equalsIgnoreCase("cursor")) {
                    add(BlockItemCommand.getCursor());
                    return;
                }

                if (arg.equalsIgnoreCase("m") || arg.equalsIgnoreCase("main")) {
                    add(BlockItemCommand.getMainHand());
                    return;
                }

                if (arg.equalsIgnoreCase("o") || arg.equalsIgnoreCase("off")) {
                    add(BlockItemCommand.getOffHand());
                    return;
                }

                add(Arrays.stream(arg.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        });
        subCommand(new Command("remove", "Remove id from filter") {
            {
                parameter(new Parameter<>("ids", new TypeString()));
            }
            public void execute(CommandContext ctx) {
                List<Integer> ids = Arrays.stream(ctx.<String>getArg(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
                ctx.sendMessage("§8[§cZalupa§8] §fRemoved ids §7- §a%s", ids.stream().map(String::valueOf).collect(Collectors.joining("§7, §a")));
                ids.forEach(Cheat.NUKER.getIdFilter()::remove);

            }
        });
        subCommand(new Command("list", "List of ids") {
            public void execute(CommandContext ctx) {
                ctx.sendMessage("§8[§cZalupa§8] §fFilter §7- §a%s",
                        Cheat.NUKER.getIdFilter().stream().map(String::valueOf).collect(Collectors.joining("§7, §a")));
            }
        });
        subCommand(new Command("clear", "Clear filter") {
            public void execute(CommandContext ctx) {
                ctx.sendMessage("§8[§cZalupa§8] §fAll ids removed");
                Cheat.NUKER.getIdFilter().clear();
            }
        });

        subCommand(new Command("click", "Click mode") {
            public void execute(CommandContext ctx) {
                clickMode = !clickMode;
                ctx.sendMessage("§8[§cZalupa§8] §f%s", clickMode ? "Enabled!" : "Disabled!");
            }
        });
    }

    public void execute(CommandContext ctx) {
        ctx.sendMessage(getUsage());
    }

    public void add(Integer... ids) {
        add(Arrays.stream(ids).collect(Collectors.toList()));
    }

    public void add(List<Integer> ids) {
        if (ids.size() == 1 && Cheat.NUKER.getIdFilter().contains(ids.get(0)))
            return;

        Command.send("§8[§cZalupa§8] §fAdd ids §7- §a%s", ids.stream().map(String::valueOf).collect(Collectors.joining("§7, §a")));
        ids.forEach(Cheat.NUKER.getIdFilter()::add);
    }
}
