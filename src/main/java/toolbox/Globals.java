package toolbox;

import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.client.Minecraft;

public interface Globals {
    Logger LOGGER = LogManager.getLogger("YellowGiraffe");
    Minecraft MC = FMLClientHandler.instance().getClient();
}
