package features;

import event.LocalPlayerUpdateEvent;
import mod.BaseMod;
import mod.Category;
import mod.ToggleMod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static toolbox.Helper.getLogger;
import static toolbox.Helper.getMinecraft;

public class FullBright extends ToggleMod {
    public FullBright(){
        super(Category.RENDER, "fullbright", false,"makes brightness");
    }

    @SubscribeEvent
    public void onUpdate(LocalPlayerUpdateEvent event){
        if(isEnabled()){
            getMinecraft().gameSettings.gammaSetting = 16;
        }else{
            getMinecraft().gameSettings.gammaSetting = 1;
            disable();
        }
    }
}
