package dev.rtt.development.rttchat.Managers;

import dev.rtt.development.rttchat.Main;
import java.util.logging.Logger;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

public class VaultHook {

  private static Chat chat;

  public VaultHook() {
    chat = null;
  }

  public static boolean hook() {
    setupChat();
    return true;
  }

  public static String getPlayerPrefix(Player player) {
    return Utils.color(chat.getPlayerPrefix(player));
  }

  public static String getPlayerSuffix(Player player) {
    return Utils.color(chat.getPlayerSuffix(player));
  }

  public static String getPlayerDisplayName(Player player) {
    String displayname = String.valueOf(chat.getPlayerPrefix(player)) + " " + player.getName();
    return Utils.color(displayname);
  }

  public static void refreshVault() {
    Chat chat = (Chat)Main.getInstance().getServer().getServicesManager().load(Chat.class);
    if (chat != chat) {
      Main.getInstance().getLogger().info("Permissions ChatManager Found: " + (chat == null ? "null" : chat.getName()));
    }
    chat = chat;
  }

  public static boolean setupChat() {
    RegisteredServiceProvider chatProvider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
    if (chatProvider != null) {
      chat = (Chat)chatProvider.getProvider();
    }
    return chat != null;
  }

}
