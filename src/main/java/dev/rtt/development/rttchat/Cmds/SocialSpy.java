package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.SpyManager;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialSpy implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (cmd.getName().equalsIgnoreCase("socialspy")) {
        Player p = (Player)sender;
        if (p.hasPermission("rttchat.socialspy")) {
          if (args.length == 0) {
            if (SpyManager.isSpyModeEnabled(p)) {
              SpyManager.disableSpyMode(p);
              p.sendMessage(Utils.getConfigMsg("SpyOff"));
            }
            else {
              SpyManager.enableSpyMode(p);
              p.sendMessage(Utils.getConfigMsg("SpyOn"));
            }
          }
          else if (args.length == 1) {
            Player p2 = Bukkit.getPlayer(args[0]);
            if (p2 != null) {
              if (SpyManager.isSpyModeEnabled(p2)) {
                SpyManager.disableSpyMode(p2);
                p.sendMessage(Utils.color("&9SpyMode &7has been &7Disabled for &c" + p2.getName()));
              }
              else {
                SpyManager.enableSpyMode(p2);
                p.sendMessage(Utils.color("&9SpyMode &7has been &7Enabled for &a" + p2.getName()));
              }
            }
            else {
              p.sendMessage(Utils.color("&cThis player is offline"));
            }
          }
        }
        else {
          Utils.sendNoPerm(p);
        }
      }
    }
    else {
      Utils.tellConsole("&cOnly players can do this");
    }
    return true;
  }

}
