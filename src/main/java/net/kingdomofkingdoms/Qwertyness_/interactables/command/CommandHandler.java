package net.kingdomofkingdoms.Qwertyness_.interactables.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class CommandHandler {
	private List<CommandLabel> commands;
	
	/*
	 * Do not create your own instance of CommandHandler to register commands.  Instead, aquire the official instance through the Interactables
	 * service listing or the CommandLabel service listing.
	 */
	public CommandHandler() {
		commands = new ArrayList<CommandLabel>();
	}
	
	/*
	 * Use this method to register an extension of CommandLabel under the Interactables plugin.  Registeres commands will be eligible for 
	 * execution by the "interactable" command. No two commands may have the same command.getName(). This rule is not case sensitve.
	 * Returns true if register is successful and false if it is not.
	 */
	public boolean registerCommand(CommandLabel command) {
		if (!this.isRegistered(command.getName())) {
			this.commands.add(command);
			return true;
		}
		return false;
	}
	
	public boolean isRegistered(String name) {
		for(CommandLabel command : this.commands) {
			if (name.equalsIgnoreCase(command.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * This method is used for the physical execution of matching args[0] -> command.getName() strings.  
	 */
	public void onCommand(Player player, String[] args) {
		for (CommandLabel command : this.commands) {
			if (command.getName().equalsIgnoreCase(args[0])) {
				command.run(player, args);
			}
		}
	}
}
