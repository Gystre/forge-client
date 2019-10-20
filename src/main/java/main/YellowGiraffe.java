package main;

import features.Test;
import features.commands.Help;
import mod.BaseMod;
import mod.ModManager;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static toolbox.Helper.getLogger;

@Mod(modid = YellowGiraffe.MODID, name = YellowGiraffe.NAME, version = YellowGiraffe.VERSION, clientSideOnly = true)
public class YellowGiraffe {
    public static final String MODID = "kyle-forgeclient";
    public static final String NAME = "Yellow Giraffe Forge Client";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if(event.getSide() == Side.CLIENT){
//            ArrayList<Class<? extends BaseMod>> test = new ArrayList<>();
//            Test feelsbadmman =  new Tst
//            test.add();
//            test.add(new Help());
//
//            getLogger().info(test.size());
//            for(BaseMod mod : test){
//                getLogger().info(mod.getModName());
//            }
            ModManager.init();
            getLogger().info(ModManager.getModList().size());
            for(BaseMod mod : ModManager.getModList()){
                getLogger().info(mod.getModName());
            }
        }
    }
}
