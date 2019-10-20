package features;

import console.ConsoleIO;
import event.LocalPlayerUpdateEvent;
import mod.Category;
import mod.ToggleMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static toolbox.Helper.getMinecraft;

public class Test extends ToggleMod {
    public Test(){
        super(Category.MISC, "test", false, "testing");
    }

    @SubscribeEvent
    public void onUpdate(LocalPlayerUpdateEvent event){
        if(isEnabled()){
            if(getMinecraft().gameSettings.gammaSetting < 16) {
                getMinecraft().gameSettings.gammaSetting = Math.min(getMinecraft().gameSettings.gammaSetting + 0.5F, 16);
                return;
            }

            if(getMinecraft().gameSettings.gammaSetting > 0.5F)
                getMinecraft().gameSettings.gammaSetting = Math.max(getMinecraft().gameSettings.gammaSetting - 0.5F, 0.5F);
            else
                unregister();
        }else{
            unregister();
        }
    }
}
