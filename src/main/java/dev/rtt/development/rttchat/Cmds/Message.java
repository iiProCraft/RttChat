package dev.rtt.development.rttchat.Cmds;

import dev.rtt.development.rttchat.Events.FormatChat;
import dev.rtt.development.rttchat.Managers.PrivateMessageManager;
import dev.rtt.development.rttchat.Managers.SpyManager;
import dev.rtt.development.rttchat.Managers.Utils;
import dev.rtt.development.rttchat.Managers.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (cmd.getName().equalsIgnoreCase("message")) {
        Player p = (Player)sender;
        if (args.length == 0) {
          p.sendMessage(Utils.color("&c/Msg <name> <message>"));
        } else if (args.length == 1) {
          p.sendMessage(Utils.color("&c/Msg <name> <message>"));
        } else if (args.length >= 2) {
          Player target = Bukkit.getPlayer(args[0]);
          if (target != null) {
            StringBuilder message = new StringBuilder();
            for (int x = 1; x < args.length; x++) {
              message.append(String.valueOf(String.valueOf(args[x])) + " ");
            }
            PrivateMessageManager.setReplyTarget(p, target);
            String msgform = Utils.getConfigMsg("Msg-Format").replace("{sender}", p.getName()).replace("{displayname}", VaultHook.getPlayerDisplayName(p));
            msgform = FormatChat.setupPlaceholderAPI(p, msgform);
            target.sendMessage(String.valueOf(msgform) + message);
            String replyform = Utils.getConfigMsg("Reply-Format").replace("{target}", target.getName()).replace("{displayname}", VaultHook.getPlayerDisplayName(p));
            replyform = FormatChat.setupPlaceholderAPI(target, replyform);
            p.sendMessage(String.valueOf(replyform) + message);
            for (Player staff : Bukkit.getOnlinePlayers())
              if (SpyManager.isSpyModeEnabled(staff))
                staff.sendMessage(Utils.getConfigMsg("SpyFormat").replace("{sender}", p.getName()).replace("{target}", target.getName()).replace("{message}", message));
          }
        }
        else
        {
          Utils.sendNoPerm(p);
        }
      } else {
        Utils.tellConsole("&cOnly players can use this command");
      }
      return true;
    }
    return false;
  }

}
