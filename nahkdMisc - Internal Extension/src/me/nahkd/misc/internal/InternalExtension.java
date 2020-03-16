package me.nahkd.misc.internal;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

import me.nahkd.misc.core.nahkdMiscPlugin;
import me.nahkd.misc.core.ext.ExtensionInfo;
import me.nahkd.misc.core.ext.MiscExtension;
import me.nahkd.misc.core.registry.Registry;
import me.nahkd.misc.internal.categories.basicmachines.AdvancedWorkbench;
import me.nahkd.misc.internal.categories.basicmachines.BasicMachines;
import me.nahkd.misc.internal.recipes.AdvancedWorkbenchRecipes;
import me.nahkd.misc.internal.recipes.CraftingTableRecipes;

public class InternalExtension implements MiscExtension {

	private nahkdMiscPlugin plugin;
	
	public InternalExtension(nahkdMiscPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public ExtensionInfo getInfo() {
		return new ExtensionInfo("nahkdMisc", "nahkd123");
	}

	@Override
	public void preRegister() {
	}

	@Override
	public void registerAll(Registry reg) {
		new CraftingTableRecipes();
		new AdvancedWorkbenchRecipes();
		
		AdvancedWorkbench ADVANCED_WORKBENCH;
		
		BasicMachines BASIC_MACHINES = new BasicMachines();
		reg.register(BASIC_MACHINES);
		reg.register(ADVANCED_WORKBENCH = new AdvancedWorkbench());
		
		// Recipe
		// idk why but nahh
		if (plugin.getServer().getRecipesFor(ADVANCED_WORKBENCH).size() <= 1) {
			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "advanced_workbench"), ADVANCED_WORKBENCH);
			recipe.shape("cwc", "c c", "c c");
			recipe.setIngredient('c', Material.COBBLESTONE);
			recipe.setIngredient('w', Material.CRAFTING_TABLE);
			plugin.getServer().addRecipe(recipe);
			plugin.getServer().getConsoleSender().sendMessage("§3[VanillaRecipes] §bAdded vanilla recipe: advanced_workbench");
		} else {
			plugin.getServer().getConsoleSender().sendMessage("§6[VanillaRecipes] §eRecipe for advanced_workbench already exists. Is this the server reload?");
		}
	}

}
