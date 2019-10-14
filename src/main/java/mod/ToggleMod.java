package mod;

public class ToggleMod extends BaseMod {
    private boolean enabled;

    public ToggleMod(Category category, String name, boolean defaultValue, String desc) {
        super(category, name, desc);
        this.enabled = defaultValue;
    }

    /**
     * Toggle mod to be on/off
     */
    public final void toggle() {
        if (isEnabled()) {
            disable();
        } else {
            enable();
        }
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public final boolean isEnabled() {
        return enabled;
    }

    @Override
    protected void onLoad() { }

    @Override
    protected void onUnload() { }

    @Override
    protected void onEnabled() { }

    @Override
    protected void onDisabled() { }
}
