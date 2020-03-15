package me.nahkd.misc.internal.categories.basicmachines;

import org.bukkit.Material;

import me.nahkd.misc.core.category.MiscCategory;
import me.nahkd.misc.core.item.ItemBuilder;

public class BasicMachines extends MiscCategory {
	
	/**
	 * Please don't judge me for "public static" thing.
	 */
	public static BasicMachines CATEGORY;
	
	public BasicMachines() {
		super(new ItemBuilder(Material.STRIPPED_OAK_LOG).displayName("§eBasic Machines").create());
		CATEGORY = this;
	}
	
}
