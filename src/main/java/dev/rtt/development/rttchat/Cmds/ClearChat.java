package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("clearchat")) {
      if (sender.hasPermission("rttchat.clearchat")) {
        if (args.length == 0) {
          Utils.clearChat();
          Bukkit.broadcastMessage(Utils.getConfigMsg("ClearChat").replace("{operator}", sender.getName()));
        }
        else if (args.length == 1) {
          Player p2 = Bukkit.getPlayer(args[0]);
          if (p2 != null) {
            Utils.clearChat(p2);
            p2.sendMessage(Utils.getConfigMsg("ClearChatPlayer").replace("{operator}", sender.getName()));
          }
          else {
            sender.sendMessage(Utils.color("&cThis player is offline"));
          }
        }
      }
      else {
        Utils.sendNoPerm(sender);
      }
    }
    return true;
  }
}
