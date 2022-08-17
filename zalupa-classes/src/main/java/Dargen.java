import dev.xdark.clientapi.ClientApi;
import dev.xdark.clientapi.entity.Entity;
import dev.xdark.clientapi.inventory.ClickType;
import dev.xdark.clientapi.item.ItemStack;
import dev.xdark.clientapi.math.BlockPos;
import dev.xdark.clientapi.original.DiggingAction;
import dev.xdark.clientapi.original.IDargen;
import dev.xdark.clientapi.util.EnumFacing;
import dev.xdark.clientapi.world.GameMode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Dargen implements IDargen {

    protected ClientApi api;

    public void blockAction(BlockPos blockPos, DiggingAction diggingAction, EnumFacing enumFacing) {
        api.clientConnection().sendPacket(new XD((XE) diggingAction.getHandle(), (acV) blockPos, ((abP) enumFacing)));
    }

    public void attack(Entity entity) {
        api.clientConnection().sendPacket(new XR((NN) entity));
    }

    public void changeGameMode(GameMode gameMode) {

    }

    @Override
    public void fall(boolean onGround) {
        api.clientConnection().sendPacket(new Xy(onGround));
    }

    public void steal(int windowId, int slot, int mouse, ClickType clickType, ItemStack itemStack, short idk) {
        api.clientConnection().sendPacket(new Xi(windowId, slot, mouse, (RX) clickType, (Vh) itemStack, idk));
    }
}
