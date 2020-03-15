package me.nahkd.misc.core.internal.ui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.nahkd.misc.core.category.MiscCategory;
import me.nahkd.misc.core.item.ItemBuilder;
import me.nahkd.misc.core.item.MiscItem;
import me.nahkd.misc.core.recipe.CustomRecipe;
import me.nahkd.misc.core.services.RegistryService;
import me.nahkd.misc.core.ui.InventoryUI;
import me.nahkd.misc.core.ui.UIEvent.ClickButton;

public class CategoriesViewUI extends InventoryUI {

	private static final int PAGE_OVERVIEW = 0x00;
	private static final int PAGE_CATEGORY = 0x01;
	private static final int PAGE_RECIPESVIEW = 0x02;
	
	int overviewPage;
	int categoriesItemsPage;
	MiscCategory selectedCategory;
	int itemRecipesPage;
	MiscItem selectedItem;
	
	int currentPage;
	
	public CategoriesViewUI() {
		super("§3Guide", 54);
		overviewPage = 0;
		categoriesItemsPage = 0;
		currentPage = PAGE_OVERVIEW;
		selectedCategory = null;
		itemRecipesPage = 0;
		selectedItem = null;
		
		fill(0, 0, 9, 1, InventoryUI.GUI_EMPTY, InventoryUI::emptyClickHandler, 0, 1, 7);
		set(1, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).create(), (ui, clicker, button, event) -> {
			if (currentPage == PAGE_OVERVIEW && overviewPage > 0) {
				overviewPage--;
				redraw(this);
			} else if (currentPage == PAGE_CATEGORY && categoriesItemsPage > 0) {
				categoriesItemsPage--;
				redraw(this);
			} else if (currentPage == PAGE_RECIPESVIEW && itemRecipesPage > 0) {
				itemRecipesPage--;
				redraw(this);
			}
			return true;
		});
		set(7, new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).create(), (ui, clicker, button, event) -> {
			if (currentPage == PAGE_OVERVIEW) {
				overviewPage++;
				redraw(this);
			} else if (currentPage == PAGE_CATEGORY) {
				categoriesItemsPage++;
				redraw(this);
			} else if (currentPage == PAGE_RECIPESVIEW) {
				itemRecipesPage++;
				redraw(this);
			}
			return true;
		});
		redraw(this);
	}
	
	public void redraw(CategoriesViewUI ui) {
		if (ui.currentPage == PAGE_OVERVIEW) drawCategories(ui);
		else if (ui.currentPage == PAGE_CATEGORY) drawCategory(ui);
		else if (ui.currentPage == PAGE_RECIPESVIEW) drawRecipesView(ui);
	}
	
	public void drawCategories(CategoriesViewUI ui) {
		RegistryService sv = RegistryService.getService();
		boolean ex;
		for (int i = 0; i < 45; i++) {
			final int catId = (overviewPage * 45) + i;
			ex = sv.getOrderedCategories().size() > catId;
			final boolean vaildButton = ex;
			ui.set(i + 9, ex? sv.getOrderedCategories().get(catId).display : null, (_ui, clicker, button, event) -> {
				if (vaildButton) {
					ui.currentPage = PAGE_CATEGORY;
					categoriesItemsPage = 0;
					selectedCategory = sv.getOrderedCategories().get(catId);
					redraw(ui);
				}
				return true;
			});
		}
		ui.set(0, InventoryUI.GUI_EMPTY, InventoryUI::emptyClickHandler);
	}
	public void drawCategory(CategoriesViewUI ui) {
		// RegistryService sv = RegistryService.getService();
		boolean ex;
		for (int i = 0; i < 36; i++) {
			final int itemIndex = (categoriesItemsPage * 45) + i;
			ex = selectedCategory.getItems().size() > itemIndex;
			final boolean vaildButton = ex;
			ui.set(i + 9, ex? selectedCategory.getItems().get(itemIndex) : null, (_ui, clicker, button, event) -> {
				if (vaildButton) {
					ui.currentPage = PAGE_RECIPESVIEW;
					itemRecipesPage = 0;
					selectedItem = selectedCategory.getItems().get(itemIndex); 
					redraw(ui);
				}
				return true;
			});
		}
		ui.fill(0, 5, 9, 1, InventoryUI.GUI_EMPTY, InventoryUI::emptyClickHandler);
		ui.set(0, InventoryUI.GUI_BACKARROW, (_ui, clicker, button, event) -> {
			currentPage = PAGE_OVERVIEW;
			redraw(ui);
			return true;
		});
	}
	public void drawRecipesView(CategoriesViewUI ui) {
		ui.set(4, selectedItem, InventoryUI::emptyClickHandler);
		ui.fill(0, 1, 9, 5, null, InventoryUI::emptyClickHandler);
		CustomRecipe rep = selectedItem.getRecipes().get(itemRecipesPage);
		rep.type.renderGUI(ui, rep);
		ui.set(0, InventoryUI.GUI_BACKARROW, (_ui, clicker, button, event) -> {
			currentPage = PAGE_CATEGORY;
			ui.set(4, InventoryUI.GUI_EMPTY, InventoryUI::emptyClickHandler);
			redraw(ui);
			return true;
		});
	}
	
}
