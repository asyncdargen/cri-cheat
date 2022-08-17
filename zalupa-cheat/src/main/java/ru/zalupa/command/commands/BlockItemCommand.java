package ru.zalupa.command.commands;

import dev.xdark.clientapi.block.state.BlockState;
import dev.xdark.clientapi.entity.EntityPlayerSP;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.item.Items;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.math.RayTraceResult;
import ru.zalupa.Zalupa;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;

public class BlockItemCommand extends Command {

    public BlockItemCommand() {
        super("blockitem", "Info about item in hand and block on cursor", "bi");
    }

    public void execute(CommandContext ctx) {
        ctx.sendMessage("§8[§cZalupa§8] §fMain hand §a%s", getMainHand());
        ctx.sendMessage("§8[§cZalupa§8] §fOff hand §a%s", getOffHand());
        ctx.sendMessage("§8[§cZalupa§8] §fBlock on cursor §a%s", getCursor());
    }

    public static int getMainHand() {
        EntityPlayerSP self = Zalupa.getSelfPlayer();

        ItemStack stackM = self.getHeldItemMainhand();
        if (stackM != null && stackM.getItem() != null && stackM.getItem() != Items.AIR)
            return stackM.getItem().getId();

        return 0;
    }

    public static int getOffHand() {
        EntityPlayerSP self = Zalupa.getSelfPlayer();

        ItemStack stackM = self.getHeldItemOffhand();
        if (stackM != null && stackM.getItem() != null && stackM.getItem() != Items.AIR)
            return stackM.getItem().getId();

        return 0;
    }

    public static int getCursor() {
        RayTraceResult rtr = Zalupa.getApi().minecraft().getMouseOver();
        if (rtr != null && rtr.getType() == RayTraceResult.Type.BLOCK && rtr.getPos() != null) {
            BlockPos pos = rtr.getPos();
            BlockState state = Zalupa.getApi().minecraft().getWorld().getBlockState(pos.getX(), pos.getY(), pos.getZ());
            if (state != null && state.getId() != 0)
                return state.getId();
        }

        return 0;
    }
}
