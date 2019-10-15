package features;

import console.ConsoleIO;
import events.LocalPlayerUpdateEvent;
import mod.Category;
import mod.ToggleMod;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import toolbox.Globals;

import static toolbox.Helper.getLocalPlayer;

public class Test extends ToggleMod implements Globals {
    public Test(){
        super(Category.MISC, "test", false,"testing");
    }


    @SubscribeEvent
//    public static void onLocalPlayerUpdate(LocalPlayerUpdateEvent event) {
    public static void onPlayerTick(TickEvent.PlayerTickEvent evt) {
        ConsoleIO.start();
        ConsoleIO.write("wuz good", ConsoleIO.HEADING);
//        ConsoleIO.incrementIndent();
        ConsoleIO.finished();
//        if(getLocalPlayer().onGround){
//            float speed = 10f;
//            float yaw = getLocalPlayer().rotationYaw * 0.017453292F;
//            getLocalPlayer().motionX -= MathHelper.sin(yaw) * (speed / 5);
//            getLocalPlayer().motionZ += MathHelper.cos(yaw) * (speed / 5);
//        }
    }
}
