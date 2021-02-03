package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RttChat implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("rttchat")) {
      if (sender.hasPermission("rttchat.command")) {
        if (args.length == 0) {
          sender.sendMessage(Utils.color("&6&lRttChat &eCreated By DevRtt"));
        }
        else if (args.length == 1) {
          if (args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("rttchat.command.help")) {
              Utils.sendHelpList(sender);
            }
            else {
              Utils.sendNoPerm(sender);
            }
          }
          else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("rttchat.command.reload")) {
              Main.getInstance().reloadConfig();
              sender.sendMessage(Utils.color("&aPlugin Reloaded"));
            }
            else {
              Utils.sendNoPerm(sender);
            }
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
