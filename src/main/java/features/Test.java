package features;

import events.LocalPlayerUpdateEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import toolbox.Globals;

import static toolbox.Helper.getLocalPlayer;

public class Test implements Globals {

    @SubscribeEvent
    public static void onLocalPlayerUpdate(LocalPlayerUpdateEvent event) {
        LOGGER.info("test");
//        if(getLocalPlayer().onGround){
//            float speed = 10f;
//            float yaw = getLocalPlayer().rotationYaw * 0.017453292F;
//            getLocalPlayer().motionX -= MathHelper.sin(yaw) * (speed / 5);
//            getLocalPlayer().motionZ += MathHelper.cos(yaw) * (speed / 5);
//        }
    }
}
