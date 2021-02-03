package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteChat implements CommandExecutor {

  public static volatile boolean chatEnabled = true;

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("togglechat")) {
      if (sender.hasPermission("rttchat.togglechat")) {
        if (chatEnabled) {
          chatEnabled = !chatEnabled;
          Bukkit.broadcastMessage(Utils.getConfigMsg("ChatMute").replace("{Prefix}", Utils.getConfigMsg("PluginPrefix")));
        }
        else if (!chatEnabled) {
          chatEnabled = true;
          Bukkit.broadcastMessage(Utils.getConfigMsg("ChatUnMute").replace("{Prefix}", Utils.getConfigMsg("PluginPrefix")));
        }
      }
      else {
        Utils.sendNoPerm(sender);
      }
    }
    return true;
  }

}
