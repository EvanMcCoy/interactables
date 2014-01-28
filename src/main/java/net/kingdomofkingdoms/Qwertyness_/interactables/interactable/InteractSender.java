package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

public enum InteractSender {
	
	PLAYER, CONSOLE;
	
	public InteractSender getInteractSender(String input) {
		if (input.equalsIgnoreCase("player")) {
			return InteractSender.PLAYER;
		}
		else if (input.equalsIgnoreCase("console")) {
			return InteractSender.CONSOLE;
		}
		else {
			return InteractSender.PLAYER;
		}
	}
	
	public String toString() {
		if (this == InteractSender.CONSOLE) {
			return "console";
		}
		else {
			return "player";
		}
	}
}
