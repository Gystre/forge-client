package main;

import console.ConsoleIO;
import features.Test;
import mod.BaseMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import static toolbox.Helper.getLogger;
import static toolbox.Helper.getModManager;

@Mod(modid = YellowGiraffe.MODID, name = YellowGiraffe.NAME, version = YellowGiraffe.VERSION, clientSideOnly = true)
public class YellowGiraffe {
    public static final String MODID = "kyleforgehacks";
    public static final String NAME = "Kyle's Forge Hacks";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT){
            getLogger().info("hello from the console!");
            getModManager().init();
//            Test test = new Test();
//            MinecraftForge.EVENT_BUS.register(test.getClass());
        }
    }
}
