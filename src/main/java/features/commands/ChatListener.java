package features.commands;

import console.ConsoleIO;
import event.ChatOutputEvent;
import mod.BaseMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toolbox.Globals;

import static mod.ModManager.getModList;
import static toolbox.Helper.getLogger;
import static toolbox.Helper.getMinecraft;

public class ChatListener implements Globals {
    @SubscribeEvent
    public void onSentMessage(ChatOutputEvent event){
        String msg = event.getMessage().trim();
        if(!msg.startsWith("."))
            return;

        event.setCanceled(true);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(msg);

        msg.toLowerCase();
        String[] parts = msg.split(" "); //[0] = modname, [1] args

        //good ol' binary search
        String key = parts[0].substring(1, msg.length());
        int start = 0;
        int end = getModList().size()-1;
        int mid = (start + end) / 2;
        BaseMod mod = null;

        while(start <= end){
            if(getModList().get(mid).getModName().compareTo(key) < 0){
                start = mid + 1;
            }else if(getModList().get(mid).getModName().compareTo(key) == 0){
                mod = getModList().get(mid);
                break;
            }else{
                end = mid - 1;
            }

            mid = (start + end) / 2;
        }

        if(mod == null){
            ConsoleIO.write("Command not found");
            return;
        }else {
            if(!mod.isEnabled()){
                mod.start();
                getLogger().info(getMinecraft().gameSettings.gammaSetting);
            }else if(mod.isEnabled()){
                mod.stop();
            }
        }
    }
}
