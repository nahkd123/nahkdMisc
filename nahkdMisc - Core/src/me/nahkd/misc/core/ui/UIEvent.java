package me.nahkd.misc.core.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface UIEvent {
	
	public static enum ClickButton {
		LEFT_CLICK,
		RIGHT_CLICK,
		SHIFT_CLICK
	}
	
	@FunctionalInterface
	public static interface UIClickEvent extends UIEvent {
		/**
		 * Call when player click
		 * @return true to cancel click
		 */
		public boolean onClick(InventoryUI ui, Player clicker, ClickButton button, InventoryClickEvent event);
	}
	
}
