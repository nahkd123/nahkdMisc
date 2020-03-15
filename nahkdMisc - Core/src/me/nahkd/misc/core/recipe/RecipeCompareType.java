package me.nahkd.misc.core.recipe;

public enum RecipeCompareType {
	
	/** Shaped recipe, must put item in specficied slot. This one is much faster to compare */
	SHAPED_FIXED,

	// /** Shaped recipe, just like minecraft crafting table */
	// SHAPED_DYNAMIC,
	
	/** Shapeless recipe, all items are put not in order */
	SHAPELESS,
	
	/** Do compare item amount */
	INGREDIENT,
	
}
