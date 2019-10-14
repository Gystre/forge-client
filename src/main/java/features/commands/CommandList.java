package features.commands;

import java.util.Collection;

import main.YellowGiraffe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public final class CommandList {
    public final SayCmd say;
    public CommandList(){
        say = register(new SayCmd());
    }

    private static IForgeRegistry<Command> registry;
    {
        if(registry != null)
            throw new IllegalStateException(
                    "Multiple instances of CommandList!");

        RegistryBuilder<Command> registryBuilder = new RegistryBuilder<>();
        registryBuilder.setName(new ResourceLocation(YellowGiraffe.MODID, "cmds"));
        registryBuilder.setType(Command.class);
        registryBuilder.disableSaving();
        registry = registryBuilder.create();
    }

    protected final <T extends Command> T register(T cmd)
    {
        cmd.setRegistryName(YellowGiraffe.MODID, cmd.getName().toLowerCase());
        registry.register(cmd);
        return cmd;
    }

    public final IForgeRegistry<Command> getRegistry()
    {
        return registry;
    }

    @SuppressWarnings("deprecation")
    public final Collection<Command> getValues()
    {
        try
        {
            return registry.getValuesCollection();

        }catch(NoSuchMethodError e)
        {
            return registry.getValues();
        }
    }

    public final Command get(String name)
    {
        ResourceLocation location =
                new ResourceLocation(YellowGiraffe.MODID, name.toLowerCase());
        return registry.getValue(location);
    }
}
