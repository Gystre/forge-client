package mod.loader;

import com.google.common.collect.Sets;
import features.Jesus;
import features.Test;
import features.commands.ChatCommand;
import mod.BaseMod;
import net.minecraftforge.common.MinecraftForge;
import toolbox.Globals;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModManager implements Globals {
    public static ArrayList<BaseMod> mods = new ArrayList<>();

    private static final ModManager INSTANCE = new ModManager();

    public static ModManager getInstance() {
        return INSTANCE;
    }

    public static void init(){
        mods.add(new ChatCommand());
        mods.add(new Test());
        mods.add(new Jesus());




        for(BaseMod mod : mods) {
            MinecraftForge.EVENT_BUS.register(mod.getClass());
            LOGGER.info("registered " + mod.getModName());
        }
    }

}
