package packet;

import events.CancelableEvent;
import net.minecraft.network.Packet;

import java.util.ArrayList;
import java.util.EventListener;

public interface PacketOutputListener extends EventListener {
    public void onSentPacket(PacketOutputEvent event);

    class PacketOutputEvent extends CancelableEvent {
        private Packet packet;

        public PacketOutputEvent(Packet packet) {
            this.packet = packet;
        }

        public Packet getPacket() {
            return packet;
        }

        public void setPacket(Packet packet) {
            this.packet = packet;
        }

        public void fire(ArrayList<PacketOutputListener> listeners)
        {
            for(PacketOutputListener listener : listeners) {
                listener.onSentPacket(this);

                if(isCancelled())
                    break;
            }
        }
    }
}
