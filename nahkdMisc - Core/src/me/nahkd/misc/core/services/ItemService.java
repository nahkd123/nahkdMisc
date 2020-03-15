package me.nahkd.misc.core.services;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.nahkd.misc.core.nahkdMiscPlugin;
import me.nahkd.misc.core.persistent.Persistent;

public class ItemService extends Service {

	private nahkdMiscPlugin plugin;
	private NamespacedKey key;
	private static ItemService SERVICE;
	
	/**
	 * Get the service
	 * @return Either service or null if the service isn't created yet.
	 */
	public static ItemService getService() {
		return SERVICE;
	}
	
	/**
	 * Create new service
	 * @param plugin
	 * @param key
	 */
	public ItemService(nahkdMiscPlugin plugin, NamespacedKey key) {
		if (SERVICE != null) throw new IllegalAccessError("You can't have access to this contructor, silly!");
		plugin.getServer().getConsoleSender().sendMessage("§3[ServiceManager/item] §bSetting up Item Service...");
		this.plugin = plugin;
		this.key = key;
		SERVICE = this;
	}

	/**
	 * Check if the item is the custom item
	 * @param item
	 * @return
	 */
	public boolean isMiscItem(ItemStack item) {
		return item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING);
	}
	/**
	 * Get ID of the item
	 * @param item The item to check
	 * @return Either the ID or null
	 */
	public String getID(ItemStack item) {
		return isMiscItem(item)? Persistent.getString(item.getItemMeta(), key) : null;
	}
	
	/**
	 * Set the ID of the item. You're adviced before use this method
	 * @param item
	 * @param id
	 */
	public void setID(ItemStack item, String id) {
		Persistent.set(item.getItemMeta().getPersistentDataContainer(), key, id);
	}
	
	/**
	 * Check if the item is not AIR, as well as not null
	 * @param item
	 * @return
	 */
	public boolean isVaild(ItemStack item) {
		return item != null && item.getType() != Material.AIR && item.getAmount() > 0;
	}
	
	/**
	 * ItemService does not have configuration stuffs
	 */
	@Override
	public void configure(String setting, Object val) {}
	@Override
	public void onShutdown() {
		SERVICE = null;
		plugin.getServer().getConsoleSender().sendMessage("§6[ServiceManager/item] §eItem Service is shutting down...");
		plugin = null;
		key = null;
	}

}
