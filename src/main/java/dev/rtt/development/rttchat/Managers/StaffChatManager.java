package dev.rtt.development.rttchat.Managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.entity.Player;

public class StaffChatManager {

  public static List<UUID> staffchat = new ArrayList();

  public static void EnableSC(Player p) {
    staffchat.add(p.getUniqueId());
  }

  public static void DisableSC(Player p) {
    staffchat.remove(p.getUniqueId());
  }

  public static boolean isSCEnabled(Player p) {
    return staffchat.contains(p.getUniqueId());
  }

}
