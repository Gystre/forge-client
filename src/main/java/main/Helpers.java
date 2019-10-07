package main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;

public class Helpers {
	public static Minecraft getMinecraft() {
		return FMLClientHandler.instance().getClient();
	}
	
	public static EntityPlayerSP getLocalPlayer() {
	    return getMinecraft().player;
	}
}
