package me.nahkd.misc.core.ext;

import me.nahkd.misc.core.nahkdMisc;
import me.nahkd.misc.core.registry.Registry;
import me.nahkd.misc.core.sptr.ExtensionNamespacedKey;

/**
 * The interface for extension. You must call @link {@link nahkdMisc#loadExtension(MiscExtension)}
 * in your main class to load extension. One plugin can have multiple extensions.
 * 
 * User can disable individual extension if they don't like it.
 * @author haiki
 *
 */
public interface MiscExtension {
	
	/**
	 * This will be called before @link {@link MiscExtension#registerAll(Registry)}
	 */
	public void preRegister();
	/**
	 * Called when the main plugin decide to registers stuffs inside the extension
	 * @param registry The registry. Use @link {@link Registry#register(me.nahkd.misc.core.registry.Registerable)}
	 */
	public void registerAll(Registry registry);
	/**
	 * Get extension info
	 * @return
	 */
	public ExtensionInfo getInfo();
	
	default public ExtensionNamespacedKey newKey(String key) {
		return new ExtensionNamespacedKey(this, key);
	}
	
}
