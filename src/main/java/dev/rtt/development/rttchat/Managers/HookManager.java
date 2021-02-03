package dev.rtt.development.rttchat.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;

public class HookManager {
  protected static PlaceholderapiHook placeholderApi;
  protected static VaultHook vault;

  public void hookManager() {
    if (Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI"))
      placeholderApi = new PlaceholderapiHook();
  }

  public static void loadDepencies() {
    if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
      vault = new VaultHook();
    }
    if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null)
      placeholderApi = new PlaceholderapiHook();
  }

  public static boolean isPlaceholderAPILoaded()
  {
    return placeholderApi != null;
  }

  public static boolean isVaultLoaded() {
    return vault != null;
  }
}
