package main;

import console.ConsoleIO;
import mod.BaseMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;
import toolbox.Helper;

import static toolbox.Helper.getModManager;

@Mod(modid = YellowGiraffe.MODID, name = YellowGiraffe.NAME, version = YellowGiraffe.VERSION, clientSideOnly = true)
public class YellowGiraffe {
    public static final String MODID = "kyleforgehacks";
    public static final String NAME = "Kyle's Forge Hacks";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(event.getSide() == Side.CLIENT){
            //init mods
            getModManager().loadAll();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT){
            getModManager().forEach(BaseMod::load);
            //getModManager().get("jesus").get().enable();
        }
    }
}
