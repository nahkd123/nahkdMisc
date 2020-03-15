package me.nahkd.misc.core.temporary;

import org.bukkit.entity.Player;

import me.nahkd.misc.core.internal.ui.CategoriesViewUI;
import me.nahkd.misc.core.ui.InventoryUI;

public class PlayerTemporaryData {
	
	Player player;
	InventoryUI openUI;
	
	CategoriesViewUI guideUI;
	
	public PlayerTemporaryData(Player player) {
		this.player = player;
		this.openUI = null;
		
		guideUI = new CategoriesViewUI();
	}
	
	public void openUI(InventoryUI ui) {
		if (ui == null) throw new NullPointerException("ui cannot be null");
		player.openInventory(ui.getInventory());
		openUI = ui;
	}
	public void closeUI() {
		player.closeInventory();
	}
	public void theUIIsClosed() {
		openUI = null;
	}
	public boolean openedCustomUI() {
		return openUI != null;
	}
	public InventoryUI getUI() {
		return openUI;
	}
	
	public void openGuide() {
		openUI(guideUI);
	}
	
}
