package mod;

import joptsimple.internal.Strings;
import net.minecraftforge.common.MinecraftForge;
import toolbox.Globals;

public abstract class BaseMod implements Globals {
    private static String modName;
    private static String modDescription;
    private final Category category;

    // is the mod registered on the forge bus?
    private boolean registered = false;

    public BaseMod(Category category, String name, String desc){
        this.category = category;
        this.modName = name;
        this.modDescription = desc;
    }

    public BaseMod(Category category, String name){
        this.category = category;
        this.modName = name;
        this.modDescription = Strings.EMPTY;
    }

    public void register(){
        if(!registered) {
            MinecraftForge.EVENT_BUS.register(this);
            registered = true;
            LOGGER.info(modName + " registered");
        }
    }

    public void unregister(){
        if(registered){
            MinecraftForge.EVENT_BUS.unregister(this);
            registered = false;
            LOGGER.info(modName + " unregistered");
        }
    }

    public void start(){
        enable();
        LOGGER.info("started " + modName);
    }

    public void stop(){
        disable();
        LOGGER.info("stopped " + modName);
    }

    protected abstract void enable();
    protected abstract void disable();
    public abstract boolean isEnabled();

    public static String getModName() {
        return modName;
    }

    public static String getModDescription() {
        return modDescription;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isRegistered() {
        return registered;
    }
}
