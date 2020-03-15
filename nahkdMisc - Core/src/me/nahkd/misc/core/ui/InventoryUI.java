package me.nahkd.misc.core.ui;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.nahkd.misc.core.item.ItemBuilder;
import me.nahkd.misc.core.ui.UIEvent.ClickButton;
import me.nahkd.misc.core.ui.UIEvent.UIClickEvent;

/**
 * The main component for creating user interface. No you can't use CustomInventory to
 * do that...
 * @author nahkd123
 *
 */
public abstract class InventoryUI {
	
	int size;
	String title;
	
	Inventory baseInventory;
	HashMap<Integer, UIEvent.UIClickEvent> clickEvents;
	
	public InventoryUI(String title, int size) {
		this.title = title;
		this.size = size;
		this.baseInventory = Bukkit.createInventory(null, size, title);
		clickEvents = new HashMap<Integer, UIEvent.UIClickEvent>();
	}
	
	/**
	 * Get the size of inventory
	 * @return
	 */
	public int getSize() {return size;}
	
	/**
	 * Get the title of inventory
	 * @return
	 */
	public String getTitle() {return title;}
	
	/**
	 * Set item in slot. This won't change event handler.
	 * @param slot
	 * @param item
	 */
	public void set(int slot, ItemStack item) {
		baseInventory.setItem(slot, item);
	}
	/**
	 * Change event handler.
	 * @param slot
	 * @param event
	 */
	public void setEvent(int slot, UIEvent.UIClickEvent event) {
		if (event == null && clickEvents.containsKey(slot)) clickEvents.remove(slot);
		else clickEvents.put(slot, event);
	}
	/**
	 * Do both of them
	 * @param slot
	 * @param item
	 * @param event
	 */
	public void set(int slot, ItemStack item, UIEvent.UIClickEvent event) {
		set(slot, item);
		setEvent(slot, event);
	}
	
	public void invokeClickEvent(InventoryClickEvent event, Player p) {
		if (clickEvents.containsKey(event.getSlot())) {
			UIEvent.ClickButton button;
			if (event.isLeftClick()) button = ClickButton.LEFT_CLICK;
			else if (event.isRightClick()) button = ClickButton.RIGHT_CLICK;
			else if (event.isShiftClick()) button = ClickButton.SHIFT_CLICK;
			else button = ClickButton.LEFT_CLICK;
			event.setCancelled(clickEvents.get(event.getSlot()).onClick(this, p, button, event));
		}
	}
	/**
	 * Call when the inventory is closing. You can override this method,
	 * but please call superclass method as well.
	 * @param event
	 */
	public void inventoryClose(InventoryCloseEvent event) {}
	
	/**
	 * Fill the inventory
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param item The item to fill
	 * @param event The event handler. null for not changing event
	 */
	public void fill(int x, int y, int w, int h, ItemStack item, UIEvent.UIClickEvent event, int... excludes) {
		for (int yy = 0; yy < h; yy++) outer: for (int xx = 0; xx < w; xx++) {
			final int acX = x + xx, acY = y + yy;
			final int slot = (acY * 9) + acX;
			if (excludes.length > 0) for (int a : excludes) if (a == slot) continue outer;
			set(slot, item);
			if (event != null) setEvent(slot, event);
		}
	}
	
	public Inventory getInventory() {
		return baseInventory;
	}
	
	/**
	 * Empty click handler. You can use "InventoryUI::emptyClickHandler" for {@link UIClickEvent}
	 * @param ui
	 * @param p
	 * @param button
	 * @param event
	 * @return Always true (or let's say, always cancel click event)
	 */
	public static boolean emptyClickHandler(InventoryUI ui, Player p, ClickButton button, InventoryClickEvent event) {
		return true;
	}
	
	/** The item to use with your own GUI. */
	public static final ItemStack GUI_EMPTY = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).displayName("§7").create();
	
	public static final ItemStack GUI_BACKARROW = new ItemBuilder(Material.ARROW).displayName("§7<< §fGo Back").create();
}
