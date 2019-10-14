package packet;

import net.minecraft.network.Packet;

import static net.minecraft.client.Minecraft.getMinecraft;

public class Connection {
    public static void sendPacket(Packet packet)
    {
        getMinecraft().getConnection().sendPacket(packet);
    }

//    public static void sendPacketBypass(Packet packet)
//    {
//        getMinecraft().getConnection().sendPacketBypass(packet);
//    }
}
