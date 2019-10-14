package mod.loader;

import com.google.common.collect.Sets;
import features.Jesus;
import features.Test;
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
        mods.add(new Jesus());


        for(int i = 0; i < mods.size(); i++) {
            mods.get(i).register();
            LOGGER.info("registered " + mods.get(i).getModName());
        }
    }

}
