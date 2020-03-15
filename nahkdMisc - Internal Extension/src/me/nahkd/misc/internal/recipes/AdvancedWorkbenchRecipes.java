package me.nahkd.misc.internal.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.nahkd.misc.core.internal.ui.CategoriesViewUI;
import me.nahkd.misc.core.item.ItemBuilder;
import me.nahkd.misc.core.recipe.CustomRecipe;
import me.nahkd.misc.core.recipe.RecipeType;

public class AdvancedWorkbenchRecipes extends RecipeType {

	private static final ItemStack ICON = new ItemBuilder(Material.CRAFTING_TABLE).create();
	
	private static AdvancedWorkbenchRecipes RECIPE;
	public static AdvancedWorkbenchRecipes getRecipeType() {
		return RECIPE;
	}
	
	public AdvancedWorkbenchRecipes() {
		RECIPE = this;
	}
	
	private static int[] slots = {19, 20, 21, 28, 29, 30, 37, 38, 39};
	
	@Override
	public void renderGUI(CategoriesViewUI ui, CustomRecipe recipe) {
		for (int i = 0; i < 9; i++) if (i < recipe.recipe.length && recipe.recipe[i] != null && recipe.recipe[i].getType() != Material.AIR)
			ui.set(slots[i], recipe.recipe[i]);
		ui.set(33, recipe.output);
		ui.set(26, null);
	}

}
