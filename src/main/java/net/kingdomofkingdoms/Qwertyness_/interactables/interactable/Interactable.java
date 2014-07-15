package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import net.kingdomofkingdoms.Qwertyness_.interactables.InteractablesPlugin;

public abstract class Interactable {
	protected InteractablesPlugin plugin;
	public String name;
	protected List<InteractCommand> commands = new ArrayList<InteractCommand>();
	protected List<String> messages = new ArrayList<String>();
	protected int cooldown;
	protected int uses;
	
	public Interactable(InteractablesPlugin plugin) {
		this.plugin = plugin;
	}
	
	/*
	 * Gets the name of the Interacable.  This value can be used to delete the interactable later or be set as the custom display
	 * name of an entity interactable.
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Gets a list of the InteractCommands to be executed by the portal.
	 */
	public List<InteractCommand> getCommands() {
		return this.commands;
	}
	
	/*
	 * Adds a command in the form of the InteractCommand object consisting of a String and an InteractSender
	 */
	public void addCommand(InteractCommand command) {
		this.commands.add(command);
	}
	
	/*
	 * Removes a command based on an index of it's position in the list.  This relies on the ability to get command indexes via 
	 * in-game command.
	 */
	public void removeCommand(int index) {
		this.commands.remove(index);
	}
	
	/*
	 * Sets all the commands of the Interactable via a list of InteractCommands.  This should be used by the addCommand() and
	 * removeCommand() methods to set the new value of a List<InteractCommand> commands field.
	 */
	public void setCommands(List<InteractCommand> commands) {
		this.commands = commands;
	}
	
	/*
	 * Gets a list of the Interactable's messages with pre-translated color codes.
	 */
	public List<String> getMessages() {
		try {
			for (int counter = 0;counter < this.messages.size();counter++) {
				String message = ChatColor.translateAlternateColorCodes('&', this.messages.get(counter));
				this.messages.set(counter, message);
			}
		} catch(NullPointerException e) {}
		return this.messages;
	}
	
	/*
	 * Adds a message in the form of a String object.
	 */
	public void addMessage(String message) {
		this.messages.add(message);
	}
	
	/*
	 * Removes a message based on an index of it's position in the list.  This relies on the ability to get message indexes via 
	 * in-game command.
	 */
	public void removeMessage(int index) {
		this.messages.remove(index);
	}
	
	/*
	 * Sets all the messages of the Interactable via a list of Strings.  This should be used by the addMessage() and
	 * removeMessage() methods to set the new value of a List<String> messages field.
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	/*
	 * Gets the current cooldown of the Interactable in the form of an int variable messured in seconds.
	 */
	public int getCooldown() {
		return this.cooldown;
	}
	
	/*
	 * Sets the cooldown of the interacable via an int containing the cooldown variable in seconds
	 */
	public void setCooldown(int seconds) {
		this.cooldown = seconds;
	}
	
	/*
	 * Gets the amount of times an individual player can use this specific Interactable.
	 */
	public int getUses() {
		return this.uses;
	}
	
	/*
	 * Sets the amount of times an individual player can use this specific Interactable.
	 */
	public void setUses(int uses) {
		this.uses = uses;
	}
	
	/*
	 * Should return the same InteractablesPlugin instance that was used when registering your plugin with Interactables.  This will be
	 * used to call the InteractablesManager.save() method.  The plugin instance is needed to inform Interatables which plugin data file
	 * to save to.
	 */
	public InteractablesPlugin getPlugin() {
		return this.plugin;
	}
	
	/*
	 * Should be used to compile a HashMap<String, Object> of FileConfiguration paths and their values.  This HashMap can than be
	 * passed to the InteractableManager.save() method to be saved into the corresponding data file provided by Interactables.
	 */
	public abstract void save();
}
