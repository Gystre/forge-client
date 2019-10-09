package features;

import static toolbox.EntityUtils.isInWater;
import static toolbox.Helper.getLocalPlayer;
import static toolbox.Helper.getModManager;

import events.LocalPlayerUpdateEvent;
import mod.BaseMod;
import mod.Category;
import mod.ToggleMod;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
}
