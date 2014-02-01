package net.kingdomofkingdoms.Qwertyness_.interactables.command;

import org.bukkit.entity.Player;

import net.kingdomofkingdoms.Qwertyness_.interactables.InteractablesPlugin;

public abstract class CommandLabel {
	/*
	 * Use this class to create commands that you can register with interactables.  A registered command will be available to users who execute
	 * "interactable" in-game.  An extension of this class cannot modify the command itself, but instead refers to the first argument in the command
	 * To create a valid extension of CommandLabel, you must create a class extending CommandLabel that will represent the command. Next you create
	 * a constructor that provides the needed fields for the CommandLabel constructor.  You must also implement the run() method which should
	 * contain the code you would like to execute when interactables detects your command's name field has been executed.
	 */
	
	/*
	 * The name of the CommandLabel is what Interactables will use to determine which command to execute.
	 * When a command is sent to Interactables, the plugin will attempt to match the name of a registered command to the first argument of the
	 * command.
	 */
	protected String name;
	
	/*
	 * Contains the text that should be displayed directly after the command to indicate the normal syntax of the command.
	 * Ex. /interactable addportalcommand "<portal_name> <PLAYER or CONSOLE> <command>". With the text in quotes being syntax.
	 */
	protected String syntax = "";
	
	/*
	 * Contains the description that will be displayed with the command name in the Interactables help page.
	 */
	protected String description;
	/*
	 * This field is used to inform Interactables which plugin the CommandLabel extension originates from.
	 */
	protected InteractablesPlugin plugin;
	
	protected CommandLabel(String name, String description, InteractablesPlugin plugin) {
		this.name = name;
		this.description = description;
		this.plugin = plugin;
	}
	
	protected CommandLabel(String name, String syntax, String description, InteractablesPlugin plugin) {
		this.name = name;
		this.syntax = syntax;
		this.description = description;
		this.plugin = plugin;
	}
	
	public String getName() {
		return name;
	}
	
	public InteractablesPlugin getPlugin() {
		return this.plugin;
	}
	
	public abstract void run(Player player, String[] args);
}
