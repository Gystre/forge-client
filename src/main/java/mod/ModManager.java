package mod;

import event.EventHandlers;
import features.FullBright;
import features.commands.ChatListener;
import features.commands.Help;
import net.minecraftforge.common.MinecraftForge;
import toolbox.Globals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ModManager implements Globals {
    private static ArrayList<BaseMod> mods;

    public static void init(){
        mods = new ArrayList<>();

        //actual mods
        mods.add(new FullBright());
        mods.add(new Help());

        //sort to prepare for binary search later
        Collections.sort(mods, Comparator.comparing(BaseMod::getModName));

        //misc stuff
        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        MinecraftForge.EVENT_BUS.register(new ChatListener());
    }

    public static ArrayList<BaseMod> getModList(){
        return mods;
    }
}
