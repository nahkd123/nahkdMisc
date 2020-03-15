package me.nahkd.misc.core.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import me.nahkd.misc.core.category.MiscCategory;
import me.nahkd.misc.core.events.EventsHandler;
import me.nahkd.misc.core.recipe.CustomRecipe;
import me.nahkd.misc.core.recipe.RecipeType;
import me.nahkd.misc.core.registry.Registerable;
import me.nahkd.misc.core.services.ItemService;

public abstract class MiscItem extends ItemStack implements Registerable, EventsHandler {
	
	public final String id;
	private HashMap<Class<? extends Event>, ArrayList<Consumer<? extends Event>>> handlers;
	private ArrayList<CustomRecipe> recipes;
	
	public MiscItem(final String id, MiscCategory category, ItemStack item) {
		super(item);
		handlers = new HashMap<Class<? extends Event>, ArrayList<Consumer<? extends Event>>>();
		recipes = new ArrayList<CustomRecipe>();
		ItemService.getService().setID(this, id);
		this.id = id;
		
		category.register(this);
	}
	
	// Don't ask me why I'm so evil, just because I don't want you to override it.
	/**
	 * Add recipe to this item
	 * @param type
	 * @param recipe
	 */
	public final void addRecipe(RecipeType type, ItemStack... recipe) {
		recipes.add(new CustomRecipe(type, this, recipe));
	}
	public final ArrayList<CustomRecipe> getRecipes() {
		return recipes;
	}
	
	/**
	 * Check the item. You don't need to override this, that's because item ID already stored
	 * inside the NBT.
	 * @param item
	 * @return
	 */
	public final boolean isItem(ItemStack item) {
		return 
				ItemService.getService().isVaild(item) &&
				ItemService.getService().isMiscItem(item) &&
				ItemService.getService().getID(item).equals(id);
	}
	
	@Override
	public HashMap<Class<? extends Event>, ArrayList<Consumer<? extends Event>>> getHandlers() {
		return handlers;
	}
	@Override
	public void preRegister() {
		for (CustomRecipe rep : recipes) rep.type.addRecipe(rep);
	}
	
}
