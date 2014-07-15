package net.kingdomofkingdoms.Qwertyness_.interactables.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.kingdomofkingdoms.Qwertyness_.interactables.Interactables;

public class Help extends CommandLabel {
	public Interactables plugin;
	public Help() {
		super("help", "Displays a list of commands and their matching syntax and description.", Bukkit.getServicesManager().load(Interactables.class));
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
		player.sendMessage(ChatColor.GREEN + "----- " + ChatColor.AQUA + "Interactables Help" + ChatColor.GREEN + " -----");
		for (int i = 0;i <= 4;i++) {
			CommandLabel command = this.plugin.getCommandHandler().commands.get(page*4+i);
			player.sendMessage(ChatColor.GREEN + "/interactable " + command.name + " " + ChatColor.GOLD + command.syntax +
					ChatColor.GREEN + " - " + command.description);
			
		}
	}
}
