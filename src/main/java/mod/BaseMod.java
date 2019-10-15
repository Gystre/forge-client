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

    /**
     * Register event to forge bus
     */
    public final boolean register() {
        if (!registered) {
            MinecraftForge.EVENT_BUS.register(this);
            LOGGER.info("registered " + modName);
            registered = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Unregister event on forge bus
     */
    public final boolean unregister() {
        if (registered) {
            MinecraftForge.EVENT_BUS.unregister(this);
            registered = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Load the mod
     */
    public final void load() {
        if (isEnabled()) {
            start();
        }
        onLoad();
    }

    /**
     * Unload the mod
     */
    public final void unload() {
        stop();
        onUnload();
    }

    /**
     * Enables the mod
     */
    protected final void start() {
        if (register()) {
            onEnabled();
            LOGGER.info(String.format("%s enabled", getModName()));
        }
    }

    protected final void stop() {
        if (unregister()) {
            onDisabled();
            LOGGER.info(String.format("%s disabled", getModName()));
        }
    }

    public void enable() {
        start();
    }

    public void disable() {
        stop();
    }

    /**
     * Check if the mod is hidden DEFAULT: true
     */
    public abstract boolean isHidden();

    /**
     * Check if the mod is enabled
     */
    public abstract boolean isEnabled();

    /**
     * Called when the mod is loaded
     */
    protected abstract void onLoad();

    /**
     * Called when unloaded
     */
    protected abstract void onUnload();

    /**
     * Called when the mod is enabled
     */
    protected abstract void onEnabled();

    /**
     * Called when the mod is disabled
     */
    protected abstract void onDisabled();

    public final String getModName() {
        return modName;
    }

    public final String getModDescription() {
        return modDescription;
    }

    public Category getCategory() {
        return category;
    }

    public final boolean isRegistered() {
        return registered;
    }

    @Override
    public String toString() {
        return getModName() + ": " + getModDescription();
    }
}
