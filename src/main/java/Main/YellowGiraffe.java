package Main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = YellowGiraffe.MODID, name = YellowGiraffe.NAME, version = YellowGiraffe.VERSION)
public class YellowGiraffe {
    public static final String MODID = "kyleforgehacks";
    public static final String NAME = "Kyle's Forge Hacks";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.getMessage().getUnformattedText();
        System.out.println("message is: " + message);
        if(message.equals("<" + Minecraft.getMinecraft().player.getName() + "> $amazing")) {
            Helper.printMessageNaked("cool");
        }
    }
}
