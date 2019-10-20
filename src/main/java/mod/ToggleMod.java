package mod;

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
    }

    @Override
    public void disable(){
        enabled = false;
        unregister();
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }
}
