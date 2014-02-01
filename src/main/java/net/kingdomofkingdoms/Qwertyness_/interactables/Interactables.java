package net.kingdomofkingdoms.Qwertyness_.interactables;

import java.util.ArrayList;
import java.util.List;

import net.kingdomofkingdoms.Qwertyness_.interactables.command.CommandHandler;
import net.kingdomofkingdoms.Qwertyness_.interactables.command.Info;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class Interactables extends JavaPlugin implements InteractablesPlugin {
	public List<InteractablesPlugin> plugins;
	private CommandHandler commandHandler;
	
	public void onEnable() {
		this.getServer().getLogger().info("Initializing plugin list...");
		this.plugins = new ArrayList<InteractablesPlugin>();
		this.getServer().getLogger().info("Registering services...");
		this.commandHandler = new CommandHandler();
		this.getServer().getServicesManager().register(CommandHandler.class, this.commandHandler, this, ServicePriority.Normal);
		this.getServer().getServicesManager().register(Interactables.class, this, this, ServicePriority.Normal);
		this.commandHandler.registerCommand(new Info());
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("interactable")) {
			if (this.commandHandler.isRegistered(args[0])) {
				this.commandHandler.onCommand(player, args);
			}
		}
		
		return true;
	}
	
	/*
	 * Allow Interactables to recognize your plugin by registering the main class.
	 */
	public void registerPlugin(InteractablesPlugin plugin) {
		plugins.add(plugin);
	}
	
	public boolean unregisterPlugin(InteractablesPlugin plugin) {
		if (plugins.contains(plugin)) {
			plugins.remove(plugin);
			return true;
		}
		return false;
	}
	
	/*
	 * Returns the CommandHanlder instance that handles all command-related inquiries of Interactables assets. These include registered plugins,
	 * and their CommandLabel extensions.
	 */
	public CommandHandler getCommandHandler() {
		return this.commandHandler;
	}

	public Interactables getInteractablesAPI() {
		return this;
	}
	
	public String getPluginDescription() {
		return this.getDescription().getDescription();
	}

	public String getVersion() {
		return this.getDescription().getVersion();
	}
}
