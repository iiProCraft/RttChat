package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Cmds.MuteChat;
import dev.rtt.development.rttchat.Managers.StaffChatManager;
import dev.rtt.development.rttchat.Managers.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ToggledChat implements Listener {

  @EventHandler
  public void onToggledChat(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    if (!StaffChatManager.isSCEnabled(p))
      if (!p.hasPermission("rttchat.toggledchat.bypass")) {
        if (!MuteChat.chatEnabled) {
          e.setCancelled(true);
          p.sendMessage(Utils.getConfigMsg("CantTalkChatMuted"));
        }
      } else {
        e.setCancelled(false);
      }
  }
}
