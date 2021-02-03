package dev.rtt.development.rttchat.Events;

import dev.rtt.development.rttchat.Cmds.MuteChat;
import dev.rtt.development.rttchat.Main;
import dev.rtt.development.rttchat.Managers.StaffChatManager;
import dev.rtt.development.rttchat.Managers.Utils;
import java.util.HashMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiSpam implements Listener {

  public static HashMap<Player, Integer> cooldownTime = new HashMap();
  public HashMap<Player, BukkitRunnable> cooldownTask;

  public AntiSpam() {
    this.cooldownTask = new HashMap();
  }

  @EventHandler
  public void onSpam(AsyncPlayerChatEvent e) {
    final Player p = e.getPlayer();
    if ((!StaffChatManager.isSCEnabled(p)) && (MuteChat.chatEnabled)) {
      int cooldownSeconds = Main.getInstance().getConfig().getInt("AntiSpam.cooldown");
      if (Main.getInstance().getConfig().getBoolean("AntiSwear.enabled"))
        if (!p.hasPermission("rttchat.antispam.bypass")) {
          if (cooldownTime.containsKey(p)) {
            e.setCancelled(true);
            p.sendMessage(Utils.getConfigMsg("AntiSpam.cooldownMsg").replace("{Prefix}", Utils.getConfigMsg("PluginPrefix")).replace("{time}", String.valueOf(cooldownTime.get(p))));
          }
          else {
            cooldownTime.put(p, Integer.valueOf(cooldownSeconds));
            this.cooldownTask.put(p, new BukkitRunnable() {
              public void run() {
                AntiSpam.cooldownTime.put(p, Integer.valueOf(((Integer)AntiSpam.cooldownTime.get(p)).intValue() - 1));
                if (((Integer)AntiSpam.cooldownTime.get(p)).intValue() == 0) {
                  AntiSpam.cooldownTime.remove(p);
                  AntiSpam.this.cooldownTask.remove(p);
                  cancel();
                }
              }
            });
            ((BukkitRunnable)this.cooldownTask.get(p)).runTaskTimer(Main.getInstance(), 20L, 20L);
          }
        }
        else
          e.setCancelled(false);
    }
  }

}
