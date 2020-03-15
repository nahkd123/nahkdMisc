package me.nahkd.misc.core.services;

import java.util.ArrayList;
import java.util.HashMap;

import me.nahkd.misc.core.nahkdMiscPlugin;
import me.nahkd.misc.core.category.MiscCategory;
import me.nahkd.misc.core.item.MiscItem;
import me.nahkd.misc.core.registry.Registerable;
import me.nahkd.misc.core.registry.Registry;

public class RegistryService extends Service implements Registry {

	private nahkdMiscPlugin plugin;
	private HashMap<Class<? extends MiscCategory>, MiscCategory> categoriesMap;
	private HashMap<String, MiscItem> itemsMap;
	private ArrayList<MiscCategory> categories;
	
	private static RegistryService SERVICE;
	public static RegistryService getService() {
		return SERVICE;
	}
	
	public RegistryService(nahkdMiscPlugin plugin) {
		if (SERVICE != null) throw new IllegalAccessError("You can't have access to this contructor, silly!");
		plugin.getServer().getConsoleSender().sendMessage("§3[ServiceManager/registry] §bSetting up Registry...");
		this.plugin = plugin;
		categoriesMap = new HashMap<Class<? extends MiscCategory>, MiscCategory>();
		itemsMap = new HashMap<String, MiscItem>();
		categories = new ArrayList<MiscCategory>();
		
		SERVICE = this;
	}
	public HashMap<String, MiscItem> getItemsMap() {
		return itemsMap;
	}
	public HashMap<Class<? extends MiscCategory>, MiscCategory> getCategories() {
		return categoriesMap;
	}
	public ArrayList<MiscCategory> getOrderedCategories() {
		return categories;
	}
	
	@Override
	public void register(Registerable registerable) {
		registerable.preRegister();
		if (registerable instanceof MiscCategory) {
			categoriesMap.put(((MiscCategory) registerable).getClass(), (MiscCategory) registerable);
			categories.add((MiscCategory) registerable);
		} else if (registerable instanceof MiscItem) itemsMap.put(((MiscItem) registerable).id, (MiscItem) registerable);
	}

	@Override
	public void configure(String setting, Object val) {}
	@Override
	public void onShutdown() {
		SERVICE = null;
		plugin.getServer().getConsoleSender().sendMessage("§6[ServiceManager/registry] §eRegistry is shutting down...");
		plugin = null;
		categoriesMap.clear();
		categoriesMap = null;
		categories.clear();
		categories = null;
	}

}
