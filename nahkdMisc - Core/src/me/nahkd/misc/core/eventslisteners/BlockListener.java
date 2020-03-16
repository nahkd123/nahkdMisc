package me.nahkd.misc.core.eventslisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.nahkd.misc.core.block.MiscBlock;
import me.nahkd.misc.core.item.MiscItem;
import me.nahkd.misc.core.services.ItemService;
import me.nahkd.misc.core.services.RegistryService;

public class BlockListener implements Listener {
	
	@EventHandler(ignoreCancelled=true)
	public void onPlace(BlockPlaceEvent event) {
		if (ItemService.getService().isMiscItem(event.getItemInHand())) {
			MiscItem item = RegistryService.getService().getItemsMap().get(ItemService.getService().getID(event.getItemInHand()));
			if (item == null) {
				event.setCancelled(true);
				event.getPlayer().sendMessage("§7>> §cYour item is invaild. Please contact administrator for information.");
			} else if (!(item instanceof MiscBlock)) event.setCancelled(true);
			
			// Block place logic
		}
	}
	
}
