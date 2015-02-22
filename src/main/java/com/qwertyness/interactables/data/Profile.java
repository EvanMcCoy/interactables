package com.qwertyness.interactables.data;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.map.MapRenderer;

public class Profile {

	private UUID owner;
	private String name;
	private int hoursPlayed;
	private String message;
	private String signature;
	
	private MapRenderer map;
	
	public Profile(UUID owner) {
		this.owner = owner;
		this.name = Bukkit.getPlayer(this.owner).getName();
	}
	
	public UUID getOwner() {
		return this.owner;
	}
	
	public void createMap() {
		
	}
}
