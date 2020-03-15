package me.nahkd.misc.core.persistent;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Persistent {

	public static void set(PersistentDataContainer container, NamespacedKey ns, Object val) {
		if (val instanceof String) container.set(ns, PersistentDataType.STRING, (String) val);
		else if (val instanceof Integer) container.set(ns, PersistentDataType.INTEGER, (Integer) val);
		else throw new IllegalArgumentException("Invaild object type");
	}
	
	public static String getString(PersistentDataContainer container, NamespacedKey ns) {
		if (container.has(ns, PersistentDataType.STRING)) return container.get(ns, PersistentDataType.STRING);
		else return null;
	}
	public static String getString(PersistentDataContainer container, NamespacedKey ns, String def) {
		if (container.has(ns, PersistentDataType.STRING)) return container.get(ns, PersistentDataType.STRING);
		else return def;
	}
	public static String getString(ItemMeta meta, NamespacedKey ns) {return getString(meta.getPersistentDataContainer(), ns);}
	public static String getString(ItemMeta meta, NamespacedKey ns, String def) {return getString(meta.getPersistentDataContainer(), ns, def);}
	
	public static int getInt(PersistentDataContainer container, NamespacedKey ns) {
		if (container.has(ns, PersistentDataType.STRING)) return container.get(ns, PersistentDataType.INTEGER);
		else return 0;
	}
	public static int getInt(PersistentDataContainer container, NamespacedKey ns, int def) {
		if (container.has(ns, PersistentDataType.STRING)) return container.get(ns, PersistentDataType.INTEGER);
		else return def;
	}
	public static int getInt(ItemMeta meta, NamespacedKey ns) {return getInt(meta.getPersistentDataContainer(), ns);}
	public static int getInt(ItemMeta meta, NamespacedKey ns, int def) {return getInt(meta.getPersistentDataContainer(), ns, def);}
	
}
