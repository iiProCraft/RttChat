package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Discord implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("discord")) {
      Player player = (Player)sender;
      if (Main.getInstance().getConfig().getBoolean("discord.enabled")) {
        Utils.sendDiscord(player);
      }
    }
    return true;
  }
}
