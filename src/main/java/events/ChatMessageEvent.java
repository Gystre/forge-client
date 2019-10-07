package events;

import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.mojang.realmsclient.dto.PlayerInfo;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ChatMessageEvent extends Event {

	  public static ChatMessageEvent newPublicChat(PlayerInfo playerInfo, String message) {
	    return new ChatMessageEvent(playerInfo, message, null);
	  }
	  
	  public static ChatMessageEvent newPrivateChat(
	      PlayerInfo sender, PlayerInfo receiver, String message) {
	    return new ChatMessageEvent(sender, message, receiver);
	  }
	  
	  private final PlayerInfo sender;
	  private final String message;
	  
	  // for private messages only
	  // null when is a public message
	  private final PlayerInfo receiver;
	  
	  public ChatMessageEvent(PlayerInfo sender, String message, PlayerInfo receiver) {
	    this.sender = sender;
	    this.message = Strings.nullToEmpty(message);
	    this.receiver = receiver;
	  }
	  
	  public PlayerInfo getSender() {
	    return sender;
	  }
	  
	  public String getMessage() {
	    return message;
	  }
	  
	  @Nullable
	  public PlayerInfo getReceiver() {
	    return receiver;
	  }
	  
	  public boolean isWhispering() {
	    return receiver != null;
	  }
}
