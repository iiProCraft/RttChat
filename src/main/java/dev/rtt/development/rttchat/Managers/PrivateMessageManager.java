package dev.rtt.development.rttchat.Managers;

import java.util.HashMap;
import org.bukkit.entity.Player;

public class PrivateMessageManager {

  public static HashMap<Player, Player> msg = new HashMap();

  public static Player getReplyTarget(Player messager) {
    return (Player)msg.get(messager);
  }

  public static void setReplyTarget(Player messager, Player reciever) {
    msg.put(messager, reciever);
    msg.put(reciever, messager);
  }
}
