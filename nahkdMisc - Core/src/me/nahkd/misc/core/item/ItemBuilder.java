package me.nahkd.misc.core.item;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	Material mat;
	int amount;
	
	boolean itemMeta;
	ArrayList<String> lore;
	String name;
	String displayName;
	
	public ItemBuilder(Material mat) {
		this.mat = mat;
		this.amount = 1;
		
		itemMeta = false;
	}
	public ItemBuilder(Material mat, int amount) {
		this.mat = mat;
		this.amount = amount;
		
		itemMeta = false;
	}
	
	/**
	 * Add lore
	 * @param lore
	 * @return
	 */
	public ItemBuilder lore(String... lore) {
		if (!itemMeta) itemMeta = true;
		if (this.lore == null) this.lore = new ArrayList<String>();
		this.lore.addAll(Arrays.asList(lore));
		return this;
	}
	/**
	 * Set localized name a.k.a the name that can't be renamed by using anvil
	 * @param localizedName
	 * @return
	 */
	public ItemBuilder name(String localizedName) {
		if (!itemMeta) itemMeta = true;
		name = localizedName;
		return this;
	}
	/**
	 * Set display name a.k.a name that's renamed from anvil
	 * @param name
	 * @return
	 */
	public ItemBuilder displayName(String name) {
		if (!itemMeta) itemMeta = true;
		displayName = name;
		return this;
	}
	
	public ItemStack create() {
		ItemStack item = new ItemStack(mat, amount);
		if (itemMeta) {
			ItemMeta meta = item.getItemMeta();
			if (name != null) meta.setLocalizedName(name);
			if (displayName != null) meta.setDisplayName(displayName);
			if (lore != null) meta.setLore(lore);
			item.setItemMeta(meta);
		}
		return item;
	}
	
}
