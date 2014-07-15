package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import net.kingdomofkingdoms.Qwertyness_.interactables.CooldownUtil;
import net.kingdomofkingdoms.Qwertyness_.interactables.Interactables;
import net.kingdomofkingdoms.Qwertyness_.interactables.InteractablesPlugin;
import net.kingdomofkingdoms.Qwertyness_.interactables.data.DataFile;

public class InteractableManager {
	Interactables plugin;
	
	/*
	 * A list containing all of the interactables currently registered under the Interactables plugin.
	 */
	List<Interactable> interactables;
	
	public InteractableManager(Interactables plugin) {
		this.plugin = plugin;
		this.interactables = new ArrayList<Interactable>();
	}
	
	public boolean registerInteractable(Interactable interactable) {
		if (!isRegistered(interactable)) {
			interactables.add(interactable);
			return true;
		}
		return false;
	}
	
	public void unregisterInteractable(Interactable interactable) {
		this.interactables.remove(interactable);
	}
	
	public boolean isRegistered(Interactable interactable) {
		return this.isRegistered(interactable.getName());
	}
	public boolean isRegistered(String name) {
		for (Interactable testInteractable : this.interactables) {
			if (name.equals(testInteractable.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public Interactable getInteractable(String name) {
		for (Interactable interactable : this.interactables) {
			if (interactable.getName().equals(name)) {
				return interactable;
			}
		}
		return null;
	}
	
	public List<Interactable> getInteractables() {
		return this.interactables;
	}
	
	public void saveAllInteractables() {
		for(Interactable interactable : this.interactables) {
			interactable.save();
		}
	}
	
	/*
	 * Saves all data of an interactable based on the originating plugin and a Hashmap of paths and their values.  Should be called by
	 * the save() method in an implementation of Interactable. Saves to the data file specified on plugin register, so your plugin must
	 * be registered with Interactables for this method to work properly.
	 */
	public void saveInteractable(InteractablesPlugin dataPlugin, HashMap<String, Object> values) {
		for (InteractablesPlugin testPlugin : this.plugin.getPlugins()) {
			if (testPlugin.getClass() == dataPlugin.getClass()) {
				for(int counter = 0;counter < values.keySet().size();counter++) {
					DataFile file = this.plugin.dataFiles.get(dataPlugin);
					file.get().set(new ArrayList<String>(values.keySet()).get(counter), new ArrayList<Entry<String, Object>>(values.entrySet()).get(counter).getValue());
					file.save();
				}
			}
		}
	}
	
	/*
	 * Deletes an interactable from is plugin's data file.  This method does not unregister the interactable.
	 */
	public void deleteInteractable(InteractablesPlugin dataPlugin, String interactablePath) {
		for (InteractablesPlugin testPlugin : this.plugin.getPlugins()) {
			if (testPlugin.getClass() == dataPlugin.getClass()) {
				this.plugin.dataFiles.get(dataPlugin).get().set(interactablePath, null);
			}
		}
	}
	
	/*
	 * Determines whether a player can use an Interactable based on the amount of uses and the Interactable's use limit as
	 * well as whether or not the player is currently waiting on cooldown.
	 */
	public boolean canUse(Player player, Interactable interactable) {
		if (CooldownUtil.isCoolingDown(player.getName(), interactable.getName())) {
			return false;
		}
		if (getUses(player, interactable) >= interactable.getUses()) {
			return false;
		}
		return true;
	}
	
	/*
	 * Gets the current amount of uses a player has on an Interactable.
	 */
	public int getUses(Player player, Interactable interactable) {
		return this.plugin.dataFiles.get(interactable.getPlugin()).get().getInt("Uses." + interactable.getName() + "." + player.getUniqueId());
	}
	
	/*
	 * Starts a cooldown timer for the player and the executed Interactable as well as adds an extra use to the player usage entry for the executed
	 * Interactable.  Should be used only directly after the execution of an Interactable.
	 */
	public void useInteractable(Player player, Interactable interactable) {
		CooldownUtil.startCooldown(player, interactable);
		int currentUses = getUses(player, interactable);
		this.plugin.dataFiles.get(interactable.getPlugin()).get().set("Uses." + interactable.getName() + "." + player.getUniqueId(), currentUses + 1);
	}
}
