package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Managers.StaffChatManager;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatFormat implements Listener {

  @EventHandler
  public void onSC(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    String msg = e.getMessage();
    if (StaffChatManager.isSCEnabled(p))
      if (p.hasPermission("Rttchat.staffchat")) {
        for (Player staff : Bukkit.getOnlinePlayers())
          if (staff.hasPermission("Rttchat.staffchat")) {
            e.setCancelled(true);
            staff.sendMessage(String.valueOf(Utils.getConfigMsg("StaffChatFormat").replace("{sender}", p.getName())) + msg);
          }
      } else {
        StaffChatManager.DisableSC(p);
        p.sendMessage(Utils.color("&cStaffChat has been disabled automatically, You are no longer have permission"));
      }
  }

}
