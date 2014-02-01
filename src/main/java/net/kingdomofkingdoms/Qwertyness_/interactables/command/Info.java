package net.kingdomofkingdoms.Qwertyness_.interactables.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.kingdomofkingdoms.Qwertyness_.interactables.Interactables;
import net.kingdomofkingdoms.Qwertyness_.interactables.InteractablesPlugin;

public class Info extends CommandLabel {

	public Info() {
		super("info", "Displays the plugins registered with Interactables.", Bukkit.getServer().getServicesManager().load(Interactables.class));
	}

	@Override
	public void run(Player player, String[] args) {
		player.sendMessage(ChatColor.GREEN + "----- " + ChatColor.GOLD + this.getPlugin().getName() + " v" + this.getPlugin().getVersion() + ChatColor.GREEN + " -----");
		for(InteractablesPlugin plugin : this.getPlugin().getInteractablesAPI().plugins) {
			player.sendMessage(plugin.getName() + " v" + plugin.getVersion() + " - " + plugin.getPluginDescription());
		}
	}

}
