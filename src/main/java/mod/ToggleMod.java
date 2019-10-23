package mod;

import console.ConsoleIO;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class ToggleMod extends BaseMod {
    private boolean enabled;

    public ToggleMod(Category category, String name, boolean defaultValue, String desc){
        super(category, name, desc);
        this.enabled = defaultValue;
    }

    @Override
    public void enable(){
        enabled = true;
        register();
        ConsoleIO.write("enabled " + getModName(), new Style().setColor(TextFormatting.GREEN));
    }

    @Override
    public void disable(){
        enabled = false;
        unregister();
        ConsoleIO.write("disabled " + getModName(), new Style().setColor(TextFormatting.RED));
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }
}
