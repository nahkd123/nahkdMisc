package me.nahkd.misc.core;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import me.nahkd.misc.core.commands.DebugTestCommand;
import me.nahkd.misc.core.eventslisteners.BlockListener;
import me.nahkd.misc.core.eventslisteners.InventoryListener;
import me.nahkd.misc.core.services.ItemService;
import me.nahkd.misc.core.services.RegistryService;
import me.nahkd.misc.core.services.Service;
import me.nahkd.misc.internal.InternalExtension;

public class nahkdMiscPlugin extends JavaPlugin {
	
	private NamespacedKey ITEM_ID;
	
	@Override
	public void onEnable() {
		// #Core and stuffs
		getServer().getConsoleSender().sendMessage("§3[Core] §bPreparing General stuffs...");
		nahkdMisc.setLogger(getServer().getConsoleSender());
		
		getServer().getConsoleSender().sendMessage("§3[Core] §bPreparing Namespaced Keys...");
		ITEM_ID = new NamespacedKey(this, "item_id");
		
		getServer().getConsoleSender().sendMessage("§3[ServiceManager] §bPreparing Services...");
		Service.setup();
		new ItemService(this, ITEM_ID);
		new RegistryService(this);

		getServer().getConsoleSender().sendMessage("§3[Core] §bDone!");
		
		// #Internals
		getServer().getConsoleSender().sendMessage("§3[Internal] §bLoading internal extension...");
		nahkdMisc.loadExtension(new InternalExtension(this));
		
		// #Commands
		getCommand("nmtest").setExecutor(new DebugTestCommand());
		
		// #Events
		getServer().getPluginManager().registerEvents(new InventoryListener(), this);
		getServer().getPluginManager().registerEvents(new BlockListener(), this);
		
		// #Schedule thing
		getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
			getServer().getConsoleSender().sendMessage("");
			getServer().getConsoleSender().sendMessage("§b================= §enahkdMisc §b=================");
			getServer().getConsoleSender().sendMessage("                 §6Version 2.0");
			getServer().getConsoleSender().sendMessage("    §a" + RegistryService.getService().getItemsMap().size() + " §3items registered.");
			getServer().getConsoleSender().sendMessage("");
			getServer().getConsoleSender().sendMessage("    §aIt's better to report bugs on Issues tab");
			getServer().getConsoleSender().sendMessage("    §aon GitHub, rather than in plugin review");
			getServer().getConsoleSender().sendMessage("    §asection.");
			getServer().getConsoleSender().sendMessage("");
			getServer().getConsoleSender().sendMessage("    §aIf you do, you'll be ignored.");
			getServer().getConsoleSender().sendMessage("§b=============================================");
			getServer().getConsoleSender().sendMessage("");
		});
	}
	
	@Override
	public void onDisable() {
		// #Prevent memory leaks
		ITEM_ID = null;
		Service.shutdownAll();
		nahkdMisc.imDoneWithMyLife();
		System.gc();
		getServer().getConsoleSender().sendMessage("§3[Core] §bBye, have a great time!");
	}
	
}
