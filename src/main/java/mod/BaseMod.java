package mod;

import joptsimple.internal.Strings;
import net.minecraftforge.common.MinecraftForge;

import static toolbox.Helper.getLogger;

public abstract class BaseMod {
    private String modName;
    private String modDescription;
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
            getLogger().info(modName + " registered");
        }
    }

    public void unregister(){
        if(registered){
            MinecraftForge.EVENT_BUS.unregister(this);
            registered = false;
            getLogger().info(modName + " unregistered");
        }
    }

    public void start(){
        enable();
        getLogger().info("started " + modName);
    }

    public void stop(){
        disable();
        getLogger().info("stopped " + modName);
    }

    //register and unregister handled in abstract functions
    public abstract void enable();
    public abstract void disable();
    public abstract boolean isEnabled();

    public String getModName() {
        return modName;
    }

    public String getModDescription() {
        return modDescription;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isRegistered() {
        return registered;
    }
}
