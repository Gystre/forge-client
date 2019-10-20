package event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static toolbox.Helper.getLocalPlayer;

public class EventHandlers {
    @SubscribeEvent
    public void onPlayerPreTick(TickEvent.PlayerTickEvent event)
    {
        if(event.phase != TickEvent.Phase.START)
            return;

        EntityPlayer player = event.player;
        if(player != getLocalPlayer())
            return;

        if(!player.world.isRemote)
            return;

        MinecraftForge.EVENT_BUS.post(new LocalPlayerUpdateEvent((EntityPlayerSP)player));
    }

    @SubscribeEvent
    public void onClientSentMessage(ClientChatEvent event){
        ChatOutputEvent event2 = new ChatOutputEvent(event.getOriginalMessage());

        if(MinecraftForge.EVENT_BUS.post(event2))
            event.setCanceled(true);

        event.setMessage(event2.getMessage());
    }
}
