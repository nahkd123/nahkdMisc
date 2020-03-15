package me.nahkd.misc.core.recipe;

import java.util.ArrayList;

import me.nahkd.misc.core.internal.ui.CategoriesViewUI;

public abstract class RecipeType {
	
	private ArrayList<CustomRecipe> recipes;
	
	public RecipeType() {
		recipes = new ArrayList<CustomRecipe>();
	}
	public ArrayList<CustomRecipe> getRecipes() {
		return recipes;
	}
	public void addRecipe(CustomRecipe recipe) {
		recipes.add(recipe);
	}
	
	/**
	 * Display to GUI. Make sure to not modify any of button on first row!
	 * @param ui The UI
	 */
	public abstract void renderGUI(CategoriesViewUI ui, CustomRecipe recipe);
	
	
}
