package com.qwertyness.interactables;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import com.qwertyness.interactables.command.CommandHandler;
import com.qwertyness.interactables.command.Help;
import com.qwertyness.interactables.command.Info;
import com.qwertyness.interactables.data.DataFile;
import com.qwertyness.interactables.interactable.InteractableManager;

public class Interactables extends JavaPlugin implements InteractablesPlugin {
	public List<InteractablesPlugin> plugins = new ArrayList<InteractablesPlugin>();
	public HashMap<InteractablesPlugin, DataFile> dataFiles = new HashMap<InteractablesPlugin, DataFile>();
	private CommandHandler commandHandler;
	private InteractableManager interactableManager;
	public static Interactables instance;
	
	public void onEnable() {
		instance = this;
		this.getServer().getLogger().info("Initializing plugin list...");
		this.plugins = new ArrayList<InteractablesPlugin>();
		this.getServer().getLogger().info("Registering services...");
		this.commandHandler = new CommandHandler();
		this.getServer().getServicesManager().register(CommandHandler.class, this.commandHandler, this, ServicePriority.Normal);
		this.getServer().getServicesManager().register(Interactables.class, this, this, ServicePriority.Normal);
		this.interactableManager = new InteractableManager(this);
		this.getServer().getServicesManager().register(InteractableManager.class, this.interactableManager, this, ServicePriority.Normal);
		CooldownUtil.plugin = this;
		// Register commands
		this.commandHandler.registerCommand(new Info());
		this.commandHandler.registerCommand(new Help());
	}
	
	public void onDisable() {
		this.interactableManager.saveAllInteractables();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (!(args.length > 0)) {
			player.sendMessage(ChatColor.RED + "Too few arguments! Use /interactable help for a list of subcommands.");
			return true;
		}
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
		try {
			for (File file : this.getDataFolder().listFiles()) {
				if (file.getName().equals(plugin.getName() + ".yml")) {
					this.dataFiles.put(plugin, new DataFile(this, file));
					return;
				}
			}
		} catch(NullPointerException e) {}
		
		DataFile file = new DataFile(this, plugin.getName());
		file.reload();
		file.save();
		dataFiles.put(plugin, file);
	}
	
	public boolean unregisterPlugin(InteractablesPlugin plugin) {
		if (plugins.contains(plugin)) {
			plugins.remove(plugin);
			return true;
		}
		return false;
	}
	
	public List<InteractablesPlugin> getPlugins() {
		return this.plugins;
	}
	
	/*
	 * Returns the CommandHanlder instance that handles all command-related inquiries of Interactables assets. These include registered plugins,
	 * and their CommandLabel extensions.
	 */
	public CommandHandler getCommandHandler() {
		return this.commandHandler;
	}
	
	/*
	 * Returns the InteractableManager that handles the registration and management of all Interactables registered with this plugin.
	 * Do not use your own instance of InteractableManager or you will not be able to register or save proprerly.
	 */
	public InteractableManager getInteractableManager() {
		return this.interactableManager;
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
