package me.nahkd.misc.internal.categories.basicmachines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.misc.core.item.ItemBuilder;
import me.nahkd.misc.core.item.MiscItem;
import me.nahkd.misc.internal.recipes.CraftingTableRecipes;

public class AdvancedWorkbench extends MiscItem {

	public AdvancedWorkbench() {
		super("ADVANCED_WORKBENCH",
				BasicMachines.CATEGORY,
				new ItemBuilder(Material.CRAFTING_TABLE)
				.displayName("§eAdvanced Workbench")
				.lore("", "§7The workbench of awesome", "§7stuff!")
				.create()
				);
		
		addRecipe(CraftingTableRecipes.getRecipeType(),
				new ItemStack(Material.COBBLESTONE), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.COBBLESTONE),
				new ItemStack(Material.COBBLESTONE), null, new ItemStack(Material.COBBLESTONE),
				new ItemStack(Material.COBBLESTONE), null, new ItemStack(Material.COBBLESTONE)
				);
	}

}
