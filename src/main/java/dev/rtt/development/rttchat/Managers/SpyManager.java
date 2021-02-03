package dev.rtt.development.rttchat.Managers;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class SpyManager {

  public static ArrayList<Player> spy = new ArrayList();

  public static void enableSpyMode(Player p) {
    spy.add(p);
  }

  public static void disableSpyMode(Player p) {
    spy.remove(p);
  }

  public static boolean isSpyModeEnabled(Player p) {
    return spy.contains(p);
  }

}
