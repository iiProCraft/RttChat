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

public class Reply implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if ((sender instanceof Player)) {
      if (cmd.getName().equalsIgnoreCase("reply")) {
        Player target = (Player)sender;
        if (args.length == 0) {
          target.sendMessage(Utils.color("&cUsage: /reply <message>"));
        }
        else if (args.length >= 1) {
          StringBuilder message = new StringBuilder();
          for (int x = 0; x < args.length; x++) {
            message.append(String.valueOf(String.valueOf(args[x])) + " ");
          }
          Player p = PrivateMessageManager.getReplyTarget(target);
          if (p == null) {
            target.sendMessage(Utils.color("&cNo body messaged you before"));
          }
          else {
            String msgform = Utils.getConfigMsg("Msg-Format").replace("{sender}", target.getName()).replace("{displayname}", VaultHook.getPlayerDisplayName(p));
            msgform = FormatChat.setupPlaceholderAPI(target, msgform);
            p.sendMessage(String.valueOf(msgform) + " " + message);
            String replyform = Utils.getConfigMsg("Reply-Format").replace("{target}", p.getName()).replace("{displayname}", VaultHook.getPlayerDisplayName(p));
            replyform = FormatChat.setupPlaceholderAPI(p, replyform);
            target.sendMessage(String.valueOf(replyform) + " " + message);
            for (Player staff : Bukkit.getOnlinePlayers()) {
              if (SpyManager.isSpyModeEnabled(staff))
                staff.sendMessage(Utils.getConfigMsg("SpyFormat").replace("{sender}", target.getName()).replace("{target}", p.getName()).replace("{message}", message));
            }
          }
        }
        else
        {
          Utils.sendNoPerm(target);
        }
      }
    }
    else {
      Utils.tellConsole("&cOnly Players can use this command");
    }
    return true;
  }

}
