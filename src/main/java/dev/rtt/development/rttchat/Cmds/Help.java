package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (cmd.getName().equalsIgnoreCase("help")) {
        Player p = (Player)sender;
        Utils.sendHelpMessage(p);
      }
    }
    else {
      Utils.tellConsole("&cOnly players can use this command");
    }
    return true;
  }
}
