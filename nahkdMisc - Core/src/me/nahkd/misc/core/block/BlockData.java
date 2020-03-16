package me.nahkd.misc.core.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;

// Soon we'll use BinaryStorage to store data
public class BlockData {
	
	ConfigurationSection config;
	final String id;
	Location location;
	Block block;
	
	public BlockData(ConfigurationSection config, Location location) {
		this.config = config;
		this.location = location;
		block = location.getBlock();
		id = config.getString("_id", "INVAILD");
	}
	
	public String getID() {return id;}
	public Location getLocation() {return location;}
	public Block getBlock() {return block;}
	public ConfigurationSection getData() {return config;}
	
	/**
	 * Move block data to new location, as well as moving the block. Calling this method
	 * must be in sync with main thread.
	 * @param newLoc
	 */
	public void move(Location newLoc) {
		location = newLoc;
		Material temp = block.getType();
		block.setType(Material.AIR);
		block = newLoc.getBlock();
		block.setType(temp);
	}
	
}
