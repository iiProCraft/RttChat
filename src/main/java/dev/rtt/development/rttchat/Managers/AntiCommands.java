package dev.rtt.development.rttchat.Managers;

import dev.rtt.development.rttchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiCommands implements Listener {

  @EventHandler
  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
    if (e.getPlayer().hasPermission("rttchat.bypass.anticommands")) {
      return;
    }
    for (String current : Main.getInstance().getConfig().getStringList("disableCommands"))
      if (e.getMessage().equalsIgnoreCase("/" + current)) {
        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("BlockedMessage")));
      }
  }
}
