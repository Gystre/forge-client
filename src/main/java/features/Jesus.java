package features;

import static toolbox.EntityUtils.*;
import static toolbox.Helper.*;

import events.AddCollisionBoxToListEvent;
import events.LocalPlayerUpdateEvent;
import mod.BaseMod;
import mod.Category;
import mod.ToggleMod;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toolbox.EntityUtils;

public class Jesus extends ToggleMod {
    private static final AxisAlignedBB WATER_WALK_AA = new AxisAlignedBB(0.D, 0.D, 0.D, 1.D, 0.99D, 1.D);

    public Jesus() {
        super(Category.PLAYER, "Jesus", false, "walk on water");
    }

    @SubscribeEvent
    public void onLocalPlayerUpdate(LocalPlayerUpdateEvent event) {
        //if (!getModManager().get(FreecamMod.class).map(BaseMod::isEnabled).orElse(false)) {
            if (isInWater(getLocalPlayer()) && !getLocalPlayer().isSneaking()) {
                getLocalPlayer().motionY = 0.1;
                if (getLocalPlayer().getRidingEntity() != null && !(getLocalPlayer().getRidingEntity() instanceof EntityBoat)) {
                    getLocalPlayer().getRidingEntity().motionY = 0.3;
                }
            }
        //}
    }

    @SubscribeEvent
    public void onAddCollisionBox(AddCollisionBoxToListEvent event) {
        if (getLocalPlayer() != null
                && (event.getBlock() instanceof BlockLiquid)
                && (EntityUtils.isDrivenByPlayer(event.getEntity())
                || EntityUtils.isLocalPlayer(event.getEntity()))
                && !(event.getEntity() instanceof EntityBoat)
                && !getLocalPlayer().isSneaking()
                && getLocalPlayer().fallDistance < 3
                && !isInWater(getLocalPlayer())
                && (isAboveWater(getLocalPlayer(), false) || isAboveWater(getRidingEntity(), false))
                && isAboveBlock(getLocalPlayer(), event.getPos())) {
            AxisAlignedBB axisalignedbb = WATER_WALK_AA.offset(event.getPos());
            if (event.getEntityBox().intersects(axisalignedbb)) {
                event.getCollidingBoxes().add(axisalignedbb);
            }
            // cancel event, which will stop it from calling the original code
            event.setCanceled(true);
        }
    }

//    @SubscribeEvent
//    public void onPacketSending(Packet event) {
//        if (event instanceof CPacketPlayer) {
//            if (isAboveWater(getLocalPlayer(), true)
//                    && !isInWater(getLocalPlayer())
//                    && !isAboveLand(getLocalPlayer())) {
//                int ticks = getLocalPlayer().ticksExisted % 2;
//                double y = FastReflection.Fields.CPacketPlayer_y.get(event);
//                if (ticks == 0) {
//                    FastReflection.Fields.CPacketPlayer_y.set(event.getPacket(), y + 0.02D);
//                }
//            }
//        }
//    }

    @SuppressWarnings("deprecation")
    private static boolean isAboveLand(Entity entity) {
        if (entity == null)
            return false;

        double y = entity.posY - 0.01;

        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); x++) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); z++) {
                BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);

                if (getWorld().getBlockState(pos).getBlock().isFullBlock(getWorld().getBlockState(pos))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isAboveBlock(Entity entity, BlockPos pos) {
        return entity.posY >= pos.getY();
    }
}
