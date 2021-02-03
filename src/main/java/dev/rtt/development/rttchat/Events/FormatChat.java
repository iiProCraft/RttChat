package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.HookManager;
import dev.rtt.development.rttchat.Managers.Utils;
import dev.rtt.development.rttchat.Managers.VaultHook;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FormatChat implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    String msg = e.getMessage();
    String format = Utils.color(Main.getInstance().getConfig().getString("ChatFormat"));
    String format2 = setupPlaceholderAPI(p, format);
    String format3 = format2.replace("{chatprefix}", VaultHook.getPlayerPrefix(p)).replace("{name}", p.getName()).replace("{chatsuffix}", VaultHook.getPlayerSuffix(p)).replace("{displayname}", VaultHook.getPlayerDisplayName(p));
    String msg2 = msg.replaceAll("%", "%%");
    e.setFormat(String.valueOf(format3) + " " + msg2);
  }

  public static String setupPlaceholderAPI(Player p, String msg) {
    String placeholders = msg;
    if ((HookManager.isPlaceholderAPILoaded()) && (PlaceholderAPI.containsPlaceholders(placeholders))) {
      placeholders = PlaceholderAPI.setPlaceholders(p, placeholders);
    }
    return placeholders;
  }

}
