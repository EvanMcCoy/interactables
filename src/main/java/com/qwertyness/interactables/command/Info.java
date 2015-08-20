package com.qwertyness.interactables.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.qwertyness.interactables.Interactables;
import com.qwertyness.interactables.InteractablesPlugin;

public class Info extends CommandLabel {

	public Info() {
		super("info", "Displays the plugins registered with Interactables.", Bukkit.getServer().getServicesManager().load(Interactables.class));
	}

	@Override
	public void run(Player player, String[] args) {
		player.sendMessage(ChatColor.GREEN + "----- " + ChatColor.GOLD + this.getPlugin().getName() + " v" + this.getPlugin().getVersion() + ChatColor.GREEN + " -----");
		player.sendMessage(ChatColor.GREEN + "Description: " + ChatColor.GOLD + this.plugin.getPluginDescription());
		player.sendMessage(ChatColor.GREEN + "Author:" + ChatColor.GOLD + Interactables.instance.getDescription().getAuthors().get(0));
		player.sendMessage(ChatColor.GREEN + "Sub-Plugins:");
		for(InteractablesPlugin plugin : this.getPlugin().getInteractablesAPI().plugins) {
			player.sendMessage(ChatColor.GREEN + plugin.getName() + " v" + plugin.getVersion() + " - " + ChatColor.GOLD + plugin.getPluginDescription());
		}
	}

}
