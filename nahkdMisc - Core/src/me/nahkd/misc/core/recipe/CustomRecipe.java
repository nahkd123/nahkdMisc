package me.nahkd.misc.core.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomRecipe {
	
	public final RecipeType type;
	// Sure I can just use getter and setter, but I feel like this one much faster...
	public ItemStack[] recipe;
	public final ItemStack output;
	
	public CustomRecipe(RecipeType type, ItemStack output, ItemStack... recipe) {
		this.type = type;
		this.output = output;
		this.recipe = recipe;
	}
	
	/**
	 * Compare input with this recipe
	 * @param compareType The compare type
	 * @param input The input
	 * @return
	 */
	public boolean compareRecipe(RecipeCompareType compareType, ItemStack... input) {
		if (compareType == RecipeCompareType.SHAPED_FIXED) {
			if (recipe.length != input.length) return false;
			for (int i = 0; i < recipe.length; i++) {
				ItemStack in = input[i], ri = recipe[i];
				if (
						((ri == null || ri.getType() == Material.AIR) &&
						!(in == null || in.getType() == Material.AIR)) ||
						(!(ri == null || ri.getType() == Material.AIR) &&
						(in == null || in.getType() == Material.AIR))
						) return false;
				else if (!ri.isSimilar(in)) return false;
			}
			return true;
		} else return false;
	}
}
