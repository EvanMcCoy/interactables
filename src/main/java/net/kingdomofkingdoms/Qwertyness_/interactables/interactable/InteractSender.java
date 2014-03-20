package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

public enum InteractSender {
	
	PLAYER, CONSOLE, SPECIAL;
	
	public static InteractSender getInteractSender(String input) {
		if (input.equalsIgnoreCase("player")) {
			return InteractSender.PLAYER;
		}
		else if (input.equalsIgnoreCase("console")) {
			return InteractSender.CONSOLE;
		}
		else if (input.equalsIgnoreCase("special")) {
			return InteractSender.SPECIAL;
		}
		return InteractSender.PLAYER;
	}
	
	public String toString() {
		if (this == InteractSender.CONSOLE) {
			return "console";
		}
		else if (this == InteractSender.SPECIAL) {
			return "special";
		}
		return "player";
	}
}
