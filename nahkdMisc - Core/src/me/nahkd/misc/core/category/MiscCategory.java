package me.nahkd.misc.core.category;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.nahkd.misc.core.item.MiscItem;
import me.nahkd.misc.core.registry.Registerable;
import me.nahkd.misc.core.registry.Registry;

public class MiscCategory implements Registerable, Registry {
	
	private ArrayList<MiscItem> items;
	// How about not using ID???
	public final ItemStack display;
	
	public MiscCategory(ItemStack display) {
		items = new ArrayList<MiscItem>();
		this.display = convertToCategoryButton(display);
	}

	@Override
	public void register(Registerable registerable) {
		if (registerable instanceof MiscItem) items.add((MiscItem) registerable);
	}
	public ArrayList<MiscItem> getItems() {
		return items;
	}
	
	public ItemStack convertToCategoryButton(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		if (meta.hasDisplayName()) lore.add("     §f" + meta.getDisplayName());
		lore.add("   §7> §eLeft Click §7to open   ");
		lore.add("");
		meta.setLore(lore);
		meta.setDisplayName("§7");
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public void preRegister() {}
	
}
