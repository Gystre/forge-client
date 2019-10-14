package events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class CancelableEvent extends Event {
    private boolean cancelled = false;

    public void cancel()
    {
        cancelled = true;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }
}
