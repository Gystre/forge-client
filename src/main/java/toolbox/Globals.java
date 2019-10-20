package toolbox;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Globals {
    Logger LOGGER = LogManager.getLogger("YellowGiraffe");
    Minecraft MC = FMLClientHandler.instance().getClient();
}
