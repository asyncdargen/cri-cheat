package ru.zalupa.command.commands;

import dev.xdark.clientapi.entity.EntityPlayerSP;
import ru.zalupa.Zalupa;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;
import ru.zalupa.command.parameter.Parameter;
import ru.zalupa.command.type.TypeInteger;

public class VClipCommand extends Command {

    public VClipCommand() {
        super("vclip", "vertical teleport");
        parameter(new Parameter<>("value", new TypeInteger()));
    }

    public void execute(CommandContext ctx) {
        int value = ctx.<Integer>getArg(0);
        EntityPlayerSP player = Zalupa.getSelfPlayer();

        player.teleport(player.getX(), player.getY() + value, player.getZ());
        ctx.sendMessage("§8[§cZalupa§8] §fTeleport for §a%s §fblocks", value);
    }
}
