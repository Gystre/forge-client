package features.commands;

import console.ConsoleIO;
import events.PacketEvent;
import mod.ServiceMod;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.client.Minecraft.getMinecraft;
import java.util.Arrays;

public class ChatCommand extends ServiceMod {
    private final CommandList cmds;

    public ChatCommand(){
        super("Chat command service", "listens to chat for commands");
        cmds = new CommandList();
    }

//    public void onSentMessage(ChatOutputEvent event) {
//        String message = event.getMessage().trim();
//        if(!message.startsWith("."))
//            return;
//
//        event.setCanceled(true);
//        getMinecraft().ingameGUI.getChatGUI().addToSentMessages(message);
//        runCommand(message.substring(1));
//    }

    @SubscribeEvent
    public static void onSendPacket(PacketEvent.Outgoing.Pre event){
//        if(event.getPacket() instanceof CPacketPlayer){
//            String message = ((CPacketChatMessage) event.getPacket()).getMessage();
//            if (!PacketHelper.isIgnored(event.getPacket()) && message.startsWith(activationCharacter.getAsString()) && message.length() > 1) {
//                // cut out the . from the message
//                String line = message.substring(1);
//                handleCommand(line);
//                event.setCanceled(true);
//            }
//        }
    }

    public void runCommand(String input) {
        String[] parts = input.split(" ");
        Command cmd = cmds.get(parts[0]);

        if(cmd == null) {
            ConsoleIO.write("Unknown command: ." + parts[0]);
            if(input.startsWith("/"))
                ConsoleIO.write("Use \".say " + input + "\" to send it as a chat command.");
            else
                ConsoleIO.write("Type \".help\" for a list of commands or \".say ."
                                + input + "\" to send it as a chat message.");
            return;
        }

        try {
            cmd.call(Arrays.copyOfRange(parts, 1, parts.length));

        }catch(Command.CmdException e) {
            e.printToChat();
        }
    }
}
