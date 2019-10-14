package features.commands;

import net.minecraft.network.play.client.CPacketChatMessage;

import static net.minecraft.client.Minecraft.getMinecraft;

public class SayCmd extends Command {
    public SayCmd()
    {
        super("say", "Sends the given chat message.", "Syntax: .say <message>");
    }

    @Override
    public void call(String[] args) throws CmdException
    {
        if(args.length < 1)
            throw new CmdSyntaxError();

        String message = String.join(" ", args);
        CPacketChatMessage packet = new CPacketChatMessage(message);
        getMinecraft().getConnection().sendPacket(packet);
    }
}
