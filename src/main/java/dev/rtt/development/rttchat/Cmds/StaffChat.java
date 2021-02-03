package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.StaffChatManager;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (cmd.getName().equalsIgnoreCase("staffchat")) {
        Player p = (Player)sender;
        if (p.hasPermission("rttchat.staffchat")) {
          if (args.length == 0) {
            if (!StaffChatManager.isSCEnabled(p)) {
              StaffChatManager.EnableSC(p);
              p.sendMessage(Utils.getConfigMsg("StaffChatEnabled"));
            }
            else {
              StaffChatManager.DisableSC(p);
              p.sendMessage(Utils.getConfigMsg("StaffChatDisabled"));
            }
          }
          else if (args.length == 1) {
            Player p2 = Bukkit.getPlayer(args[0]);
            if (p.hasPermission("rttchat.staffchat.other")) {
              if (p2 != null) {
                if (!StaffChatManager.isSCEnabled(p2)) {
                  StaffChatManager.EnableSC(p2);
                  p.sendMessage(Utils.color("&dStaffChat &7Enabled for &b{name}").replace("{name}", p2.getName()));
                }
                else {
                  StaffChatManager.DisableSC(p2);
                  p.sendMessage(Utils.color("&eStaffChat &7Disabled for &c{name}").replace("{name}", p2.getName()));
                }
              }
              else {
                p.sendMessage(Utils.color("&cThis player is offline"));
              }
            }
            else {
              Utils.sendNoPerm(p);
            }
          }
        }
        else {
          Utils.sendNoPerm(p);
        }
      }
    }
    else {
      Utils.tellConsole("&cOnly players can use this command");
    }
    return true;
  }

}
