package event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class LocalPlayerUpdateEvent extends Event {
    private final EntityPlayerSP player;

    public LocalPlayerUpdateEvent(EntityPlayerSP player){
        this.player = player;
    }

    public EntityPlayerSP getPlayer(){
        return player;
    }
}
