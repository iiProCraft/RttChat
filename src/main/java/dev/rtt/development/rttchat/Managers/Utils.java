package dev.rtt.development.rttchat.Managers;

import dev.rtt.development.rttchat.Events.FormatChat;
import dev.rtt.development.rttchat.Main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Utils {

  public static String color(String s) {
    return ChatColor.translateAlternateColorCodes('&', s);
  }

  public static String getConfigMsg(String s) {
    return color(Main.getInstance().getConfig().getString(s));
  }

  public static void sendHelpList(Player p) {
    List help = new ArrayList();
    String lines = "&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==";
    help.add("&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==");
    help.add(" ");
    help.add("       &6&lRtt&e&lChat       ");
    help.add("  &f&lOptional &7[] &8, &f&lRequired &7<>");
    help.add(" ");
    help.add("&6+ &e/RttChat <help> &f- &6Display info");
    help.add("&6+ &e/RttChat <reload> &f- &6Reload Config");
    help.add("&6+ &e/ToggleChat &f- &6Mute and Unmute Chat");
    help.add("&6+ &e/ClearChat [-s] [name] &f- &6Clear Chat Global chat or for player");
    help.add("&6+ &e/StaffChat [name] &f- &6Toggles staffchat on and off");
    help.add("&6+ &e/Help &f- &6Display information about the server");
    help.add("&6+ &e/Msg &f- &6Private message a Player");
    help.add("&6+ &e/Reply &f- &6Reply to a private message");
    help.add(" ");
    help.add("&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==");
    for (String hline : help)
      p.sendMessage(color(hline));
  }

  public static void sendHelpList(CommandSender sender) {
    List help = new ArrayList();
    String lines = "&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==";
    help.add("&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==");
    help.add(" ");
    help.add("       &6&lRtt&e&lChat       ");
    help.add("  &f&lOptional &7[] &8, &f&lRequired &7<>");
    help.add(" ");
    help.add("&6+ &e/RttChat <help> &f- &6Display info");
    help.add("&6+ &e/RttChat <reload> &f- &6Reload Config");
    help.add("&6+ &e/ToggleChat &f- &6Mute and Unmute Chat");
    help.add("&6+ &e/ClearChat [-s] [name] &f- &6Clear Chat Global chat or for player");
    help.add("&6+ &e/StaffChat [name] &f- &6Toggles staffchat on and off");
    help.add("&6+ &e/Broadcast <message> &f- &6Broadcast a message");
    help.add("&6+ &e/Socialspy &f- &6Display all players msg logs");
    help.add("&6+ &e/Rules &f- &6View server rules");
    help.add("&6+ &e/Help &f- &6Display information about the server");
    help.add("&6+ &e/Msg &f- &6Private message a Player");
    help.add("&6+ &e/Reply &f- &6Reply to a private message");
    help.add(" ");
    help.add("&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==&7&l&m==&8&l&m==");
    for (String hline : help)
      sender.sendMessage(color(hline));
  }

  public static void clearChat()
  {
    for (int x = 0; x < 102; x++)
      Bukkit.broadcastMessage(" ");
  }

  public static void clearChat(Player p)
  {
    for (int y = 0; y < 102; y++)
      p.sendMessage(" ");
  }

  public static void sendNoPerm(Player p)
  {
    String prefix = Main.getInstance().getConfig().getString("PluginPrefix");
    String noperms = Main.getInstance().getConfig().getString("NoPermissions");
    p.sendMessage(color(noperms.replace("{Prefix}", prefix)));
  }

  public static void sendNoPerm(CommandSender sender) {
    String prefix = Main.getInstance().getConfig().getString("PluginPrefix");
    String noperms = Main.getInstance().getConfig().getString("NoPermissions");
    sender.sendMessage(color(noperms.replace("{Prefix}", prefix)));
  }

  public static void tellConsole(String s) {
    Bukkit.getServer().getConsoleSender().sendMessage(color(s));
  }

  public static void sendJoinMotd(Player p) {
    for (String line : Main.getInstance().getConfig().getStringList("Motd.message")) {
      String format = FormatChat.setupPlaceholderAPI(p, line);
      p.sendMessage(color(format.replace("{name}", p.getName())));
    }
  }

  public static void sendServerRules(Player p) {
    for (String line : Main.getInstance().getConfig().getStringList("Rules.message"))
      p.sendMessage(color(line));
  }

  public static void sendHelpMessage(Player p) {
    for (String line : Main.getInstance().getConfig().getStringList("Help.message"))
      p.sendMessage(color(line));
  }

  public static void sendDiscord(Player p) {
    for (String line : Main.getInstance().getConfig().getStringList("Discord.message"))
      p.sendMessage(color(line));
  }

  public static void clearChat(CommandSender sender) {
    for (int y = 0; y < 102; y++)
      sender.sendMessage(" ");
  }

}
