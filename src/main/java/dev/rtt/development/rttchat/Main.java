package dev.rtt.development.rttchat;

import dev.rtt.development.rttchat.Cmds.Broadcast;
import dev.rtt.development.rttchat.Cmds.ClearChat;
import dev.rtt.development.rttchat.Cmds.Help;
import dev.rtt.development.rttchat.Cmds.Message;
import dev.rtt.development.rttchat.Cmds.MuteChat;
import dev.rtt.development.rttchat.Cmds.Reply;
import dev.rtt.development.rttchat.Cmds.RttChat;
import dev.rtt.development.rttchat.Cmds.Rules;
import dev.rtt.development.rttchat.Cmds.SocialSpy;
import dev.rtt.development.rttchat.Cmds.StaffChat;
import dev.rtt.development.rttchat.Events.AntiSpam;
import dev.rtt.development.rttchat.Events.AntiSwear;
import dev.rtt.development.rttchat.Events.FormatChat;
import dev.rtt.development.rttchat.Events.JoinQuitMessages;
import dev.rtt.development.rttchat.Events.StaffChatFormat;
import dev.rtt.development.rttchat.Events.ToggledChat;
import dev.rtt.development.rttchat.Managers.AntiCommands;
import dev.rtt.development.rttchat.Managers.HookManager;
import dev.rtt.development.rttchat.Managers.Utils;
import dev.rtt.development.rttchat.Managers.VaultHook;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin {

  private static Main instance;

  @Override
  public void onEnable() {
    Bukkit.getConsoleSender().sendMessage("");
    Bukkit.getConsoleSender().sendMessage("§7§l§m------------------------------");
    Bukkit.getConsoleSender().sendMessage("§6         Rtt Chat");
    Bukkit.getConsoleSender().sendMessage("§e          by §fDevRtt");
    Bukkit.getConsoleSender().sendMessage("§a         Has been Enabled!");
    Bukkit.getConsoleSender().sendMessage("§7§l§m------------------------------");
    Bukkit.getConsoleSender().sendMessage("");
    instance = this;
    loadConfig();
    VaultHook.setupChat();
    VaultHook.refreshVault();
    registerEvents();
    registerCmds();
    loadConfig();
    autobc();
  }

  public void registerEvents() {
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new ToggledChat(), this);
    pm.registerEvents(new AntiSpam(), this);
    pm.registerEvents(new StaffChatFormat(), this);
    pm.registerEvents(new JoinQuitMessages(), this);
    pm.registerEvents(new AntiSwear(), this);
    pm.registerEvents(new FormatChat(), this);
    pm.registerEvents(new AntiCommands(), this);
  }

  public void registerCmds() {
    getCommand("staffchat").setExecutor(new StaffChat());
    getCommand("togglechat").setExecutor(new MuteChat());
    getCommand("rttchat").setExecutor(new RttChat());
    getCommand("rules").setExecutor(new Rules());
    getCommand("clearchat").setExecutor(new ClearChat());
    getCommand("broadcast").setExecutor(new Broadcast());
    getCommand("help").setExecutor(new Help());
    getCommand("message").setExecutor(new Message());
    getCommand("reply").setExecutor(new Reply());
    getCommand("socialspy").setExecutor(new SocialSpy());
  }
  public void loadConfig() {
    getConfig().options().copyDefaults();
    saveDefaultConfig();
  }

  public static Main getInstance() {
    return instance;
  }
  public boolean setupVault() {
    if (HookManager.isVaultLoaded()) {
      VaultHook.hook();
      return true;
    }
    getLogger().info("Make sure you have Vault plugin");
    Bukkit.getPluginManager().disablePlugin(this);
    return false;
  }

  public void autobc() {
    if (getConfig().getBoolean("AutoBroadCast.enabled")) {
      final List autobc$1 = getConfig().getStringList("AutoBroadCast.message");
      Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable()
      {
        public void run() {
          for (String msg : autobc$1)
            Bukkit.broadcastMessage(Utils.color(msg));
        }
      }
      , 0L, getConfig().getInt("AutoBroadCast.interval") * 20);
    }
  }
}
