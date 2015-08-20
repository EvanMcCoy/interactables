package com.qwertyness.interactables.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.qwertyness.interactables.Interactables;

public class Help extends CommandLabel {
	public Interactables plugin;
	public Help() {
		super("help", "Displays a list of commands and their matching syntax and description.", "<page>", Bukkit.getServicesManager().load(Interactables.class));
		this.plugin = Bukkit.getServicesManager().load(Interactables.class);
	}

	@Override
	public void run(Player player, String[] args) {
		int page = 0;
		if (args.length > 1) {
			page = Integer.parseInt(args[1])-1;
			if (page < 0) {
				page = 0;
			}
		}
		player.sendMessage(ChatColor.GREEN + "----- " 
				+ ChatColor.AQUA + "Interactables Help <" 
				+ ChatColor.GREEN + (page+1) 
				+ ChatColor.AQUA + "/" 
				+ ChatColor.GREEN + ((int)Math.ceil(this.plugin.getCommandHandler().commands.size()/3)+1) 
				+ ChatColor.AQUA + ">" 
				+ ChatColor.GREEN + " -----");
		for (int i = 0;i < 3;i++) {
			CommandLabel command = null;
			try {
				command = this.plugin.getCommandHandler().commands.get(page*3+i);
			} catch(IndexOutOfBoundsException e) {continue;}
			player.sendMessage(ChatColor.GREEN + "/interactable " + command.name + " " + ChatColor.GOLD + command.syntax +
					ChatColor.GREEN + " - " + command.description);
		}
		player.sendMessage(ChatColor.AQUA + "Use /interactable " + this.name + " " + this.syntax + " to see more commands!");
	}
}
