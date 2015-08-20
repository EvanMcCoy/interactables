package com.qwertyness.interactables.interactable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class InteractCommand {
	private String command;
	private InteractSender sender;
	
	public InteractCommand(String command, InteractSender sender) {
		this.command = command;
		this.sender = sender;
	}
	/*
	 * Gets the command field via a String.  This will be used in a dispatchCommand() call later to execute the command.
	 */
	public String getCommand() {
		return this.command;
	}
	
	/*
	 * Sets the command field of the current InteractCommand object. This changes only the String used in the dispatchCommand()
	 * call.
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	
	/*
	 * This gets the sender that should be used when executing the command via an InteractSender instance.  InteractSender is an
	 * enum with two-option enum allowing the use of a CONSOLE sender or sending the command as the PLAYER who executes the
	 * Interactable.
	 */
	public InteractSender getSender() {
		return this.sender;
	}
	
	/*
	 * This sets the sender in which the command will be executed from by the use of the InteractSender enum.
	 */
	public void setSender(InteractSender sender) {
		this.sender = sender;
	}
	
	@SuppressWarnings("unchecked")
	public Entry<String, String> toEntry() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(this.command, this.sender.toString());
		return (Entry<String, String>) map.entrySet().toArray()[0];
	}
	
	public static List<InteractCommand> toCommandList(List<String> input)  {
		List<InteractCommand> output = new ArrayList<InteractCommand>();
		for (String string : input) {
			String key = string.substring(0, string.lastIndexOf(':'));
			String value = string.substring(string.lastIndexOf(':')+1);
			output.add(new InteractCommand(key, InteractSender.getInteractSender(value)));
		}
		return output;
	}
	
	public static List<String> toStringList(List<InteractCommand> input) {
		List<String> output = new ArrayList<String>();
		for (InteractCommand command : input) {
			output.add(command.getCommand() + ":" + command.getSender().toString());
		}
		return output;
	}
}
