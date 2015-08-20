package com.qwertyness.interactables;

public interface InteractablesPlugin {
	/*
	 * In order to register your plugin under Interactables, you must implement it to InteractablesPlugin.
	 */
	
	/*
	 * Returns the name that you would like to display for your plugin in the Interactables info command.  If your main class extends JavaPlugin,
	 * this method is already taken care of with a name matching the name you specifies in plugin.yml
	 */
	public String getName();
	
	/*
	 * Returns the description that you would like to display for your plugin in the Interactables info command.  If your main class extends JavaPlugin,
	 * you can get the description from your plugin.yml with "this.getDescription().getDescription()".
	 */
	public String getPluginDescription();
	
	/*
	 * Returns the version that you would like to display for your plugin in the Interactables info command.  If your main class extends JavaPlugin,
	 * you can get the version from your plugin.yml with "this.getDescription().getVersion()".
	 */
	public String getVersion();

	/*
	 * Should return the global Interacables instance.  This can be used to enable certain functions outside of your main class.
	 * This method relies on a local field containing the global Interactables instance.
	 */
	public Interactables getInteractablesAPI();
}
