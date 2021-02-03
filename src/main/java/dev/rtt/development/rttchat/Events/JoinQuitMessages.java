package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.Utils;
import dev.rtt.development.rttchat.Managers.VaultHook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitMessages implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    FileConfiguration config = Main.getInstance().getConfig();
    Player p = e.getPlayer();
    if (config.getBoolean("Motd.enabled")) {
      Utils.sendJoinMotd(p);
      if (config.getBoolean("JoinMessage.enabled")) {
        String format = Utils.getConfigMsg("JoinMessage.message");
        format = format.replace("{name}", p.getName()).replace("{chatprefix}", VaultHook.getPlayerPrefix(p));
        format = FormatChat.setupPlaceholderAPI(p, format);
        e.setJoinMessage(format);
      }
      else {
        e.setJoinMessage(null);
      }
    }
  }

  @EventHandler
  public void onJoin(PlayerQuitEvent e) {
    FileConfiguration config = Main.getInstance().getConfig();
    Player p = e.getPlayer();
    if (config.getBoolean("QuitMessage.enabled")) {
      String format = Utils.getConfigMsg("QuitMessage.message");
      format = format.replace("{name}", p.getName()).replace("{chatprefix}", VaultHook.getPlayerPrefix(p));
      format = FormatChat.setupPlaceholderAPI(p, format);
      e.setQuitMessage(format);
    }
    else {
      e.setQuitMessage(null);
    }
  }

}
