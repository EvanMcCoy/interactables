package net.kingdomofkingdoms.Qwertyness_.interactables.interactable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
		for (Interactable testInteractable : this.interactables) {
			if (interactable.getName().equals(testInteractable.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public List<Interactable> getInteractables() {
		return this.interactables;
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
					file.get().set(new ArrayList<String>(values.keySet()).get(counter), new ArrayList<Entry<String, Object>>().get(counter).getValue());
				}
			}
		}
	}
}
