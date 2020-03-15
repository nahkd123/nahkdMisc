package me.nahkd.misc.core.internal.test;

import org.bukkit.event.inventory.InventoryCloseEvent;

import me.nahkd.misc.core.ui.InventoryUI;

public class TestUI extends InventoryUI {

	public TestUI() {
		super("§3Welcum to the test!", 9);
		setEvent(0, (ui, clicker, button, event) -> {
			clicker.sendMessage("ayyyyyyy");
			return true;
		});
	}
	
	@Override
	public void inventoryClose(InventoryCloseEvent event) {
		event.getPlayer().sendMessage("gay");
		super.inventoryClose(event);
	}

}
