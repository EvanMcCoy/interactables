 package com.qwertyness.interactables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import com.qwertyness.interactables.interactable.Interactable;

public class CooldownUtil {
	public static Interactables plugin;
	private static List<HashMap<String, String>> cooldownList = new ArrayList<HashMap<String, String>>();
	
	public static void addEntry(String playerName, String interactableName) {
		HashMap<String, String> hash = new HashMap<String, String>();
		hash.put(playerName, interactableName);
		cooldownList.add(hash);
	}
	
	public static void removeEntry(String playerName, String interactableName) {
		if (isCoolingDown(playerName, interactableName)) {
			cooldownList.remove((int)getCoolingDown(playerName, interactableName));
		}
	}
	
	public static Integer getCoolingDown(String playerName, String interactableName) {
		for (int counter = 0;counter < cooldownList.size();counter++) {
			HashMap<String, String> hash = cooldownList.get(counter);
			if (hash.containsKey(playerName)) {
				if (hash.get(playerName).equals(interactableName)) {
					return counter;
				}
			}
		}
		return null;
	}
	
	public static boolean isCoolingDown(String playerName, String interactableName) {
		if (getCoolingDown(playerName, interactableName) == null) {
			return false;
		}
		return true;
	}
	
	public static void startCooldown(final Player player, final Interactable interactable) {
		addEntry(player.getName(), interactable.getName());
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.runTaskLaterAsynchronously(plugin, new Runnable() {
			public void run() {
				removeEntry(player.getName(), interactable.getName());
			}
		}, interactable.getCooldown()*20);
	}
}
