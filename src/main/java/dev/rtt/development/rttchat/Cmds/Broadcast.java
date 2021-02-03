package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("broadcast")) {
      if (sender.hasPermission("rttchat.broadcast")) {
        if (args.length == 0) {
          sender.sendMessage(Utils.color("&c/Broadcast <message>"));
        }
        else if (args.length >= 1) {
          StringBuilder msg = new StringBuilder();
          for (int m = 0; m < args.length; m++) {
            msg.append(String.valueOf(String.valueOf(args[m])) + " ");
          }
          Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Utils.getConfigMsg("BroadCast").replace("{message}", msg)));
        }
      }
      else {
        Utils.sendNoPerm(sender);
      }
    }
    return true;
  }
}
