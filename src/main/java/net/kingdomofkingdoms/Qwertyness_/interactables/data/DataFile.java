package net.kingdomofkingdoms.Qwertyness_.interactables.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import net.kingdomofkingdoms.Qwertyness_.interactables.Interactables;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataFile {
	File file = null;
	FileConfiguration config = null;
	Interactables plugin;
	
	/*
	 * Represents the name of the plugin who owns it.  The name is also used as the file name.
	 */
	String name;
	
	public DataFile(Interactables plugin, String name) {
		this.plugin = plugin;
		this.name = name;
	}
	
	public DataFile(Interactables plugin, File file) {
		this.file = file;
		new YamlConfiguration();
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void reload() {
	    if (file == null) {
	    	file = new File(plugin.getDataFolder(), this.name + ".yml");
	    }
	    config = YamlConfiguration.loadConfiguration(file);
	 
	    InputStream defConfigStream = plugin.getResource(this.name + ".yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        config.setDefaults(defConfig);
	    }
	}
	public FileConfiguration get() {
	    if (config == null) {
	        this.reload();
	    }
	    return config;
	}
	public void save() {
	    if (config == null || file == null) {
	    return;
	    }
	    try {
	        get().save(file);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + file, ex);
	    }
	}
}
