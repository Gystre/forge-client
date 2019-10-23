package main;

import mod.ModManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import static toolbox.Helper.getLogger;

@Mod(modid = YellowGiraffe.MODID, name = YellowGiraffe.NAME, version = YellowGiraffe.VERSION, clientSideOnly = true)
public class YellowGiraffe {
    public static final String MODID = "kyle-forgeclient";
    public static final String NAME = "Yellow Giraffe Forge Client";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT){
            ModManager.init();
        }
    }
}
