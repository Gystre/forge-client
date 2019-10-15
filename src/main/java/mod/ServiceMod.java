package mod;

public class ServiceMod extends BaseMod{
    public ServiceMod(String name, String desc) {
        super(Category.SERVICE, name, desc);
    }

    public ServiceMod(String name) {
        super(Category.SERVICE, name);
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    protected void onLoad() {
    }

    @Override
    protected void onUnload() {
    }

    @Override
    protected void onEnabled() {
    }

    @Override
    protected void onDisabled() {
    }
}
