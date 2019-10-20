package mod;

public class ServiceMod extends BaseMod {
    public ServiceMod(String name, String desc){
        super(Category.SERVICE, name, desc);
    }

    public ServiceMod(String name){
        super(Category.SERVICE, name);
    }

    @Override
    protected void enable() { }

    @Override
    protected void disable() { }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
