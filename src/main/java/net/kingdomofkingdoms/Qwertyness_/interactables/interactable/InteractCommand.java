package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

public interface InteractCommand {
	
	/*
	 * Gets the command field via a String.  This will be used in a dispatchCommand() call later to execute the command.
	 */
	public String getCommand();
	
	/*
	 * Sets the command field of the current InteractCommand object. This changes only the String used in the dispatchCommand()
	 * call.
	 */
	public void setCommand(String command);
	
	/*
	 * This gets the sender that should be used when executing the command via an InteractSender instance.  InteractSender is an
	 * enum with two-option enum allowing the use of a CONSOLE sender or sending the command as the PLAYER who executes the
	 * Interactable.
	 */
	public InteractSender getSender();
	
	/*
	 * This sets the sender in which the command will be executed from by the use of the InteractSender enum.
	 */
	public void setSender(InteractSender sender);
}
