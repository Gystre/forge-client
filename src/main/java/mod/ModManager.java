package mod;

import event.EventHandlers;
import features.Test;
import features.commands.ChatListener;
import features.commands.CommandMod;
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

        mods.add(new Test());
        mods.add(new Help());

        Collections.sort(mods, new Comparator<BaseMod>() {
            @Override
            public int compare(BaseMod o1, BaseMod o2) {
                return o1.getModName().compareTo(o2.getModName());
            }
        });

        MinecraftForge.EVENT_BUS.register(new EventHandlers());
        MinecraftForge.EVENT_BUS.register(new ChatListener());
    }

    public static ArrayList<BaseMod> getModList(){
        return mods;
    }
}
