package event;

import com.google.common.base.Strings;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class ChatOutputEvent extends Event {
    private String message;
    private final String originalMessage;

    public ChatOutputEvent(String msg) {
        this.message = msg;
        originalMessage = Strings.nullToEmpty(message);
    }

    public String getMessage() {
        return message;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }
}
