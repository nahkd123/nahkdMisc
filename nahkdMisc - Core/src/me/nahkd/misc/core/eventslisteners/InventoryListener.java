package me.nahkd.misc.core.eventslisteners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.nahkd.misc.core.nahkdMisc;
import me.nahkd.misc.core.temporary.PlayerTemporaryData;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player) {
			Player p = (Player) event.getWhoClicked();
			PlayerTemporaryData dat = nahkdMisc.getData(p);
			if (dat.openedCustomUI() && event.getClickedInventory() == event.getInventory()) dat.getUI().invokeClickEvent(event, p);
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if (event.getPlayer() instanceof Player) {
			Player p = (Player) event.getPlayer();
			PlayerTemporaryData dat = nahkdMisc.getData(p);
			if (dat.openedCustomUI() && dat.getUI().getInventory() == event.getInventory()) {
				dat.getUI().inventoryClose(event);
				dat.theUIIsClosed();
			}
		}
	}
	
}
