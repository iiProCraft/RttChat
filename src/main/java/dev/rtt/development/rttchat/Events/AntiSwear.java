package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.StaffChatManager;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AntiSwear implements Listener {

  @EventHandler
  public void onSwear(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    if ((!StaffChatManager.isSCEnabled(p)) && (!p.hasPermission("rttchat.antiswear.bypass"))) {
      String curseMessage = e.getMessage().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").replaceAll("\\s+", "");
      for (String badwords : Main.getInstance().getConfig().getStringList("Words.BannedWords"))
        if (curseMessage.contains(badwords))
          if (Main.getInstance().getConfig().getBoolean("AntiSwear.replace")) {
            e.setMessage(curseMessage.replace(badwords, Utils.getConfigMsg("AntiSwear.replaceWith")));
          }
          else {
            e.setCancelled(true);
            p.sendMessage(Utils.getConfigMsg("AntiSwear.noSwearMsg").replace("{Prefix}", Utils.getConfigMsg("PluginPrefix")));
          }
    }
  }

}
