package me.nahkd.misc.core.sptr;

import me.nahkd.misc.core.ext.MiscExtension;

public class ExtensionNamespacedKey {
	
	final MiscExtension ext;
	final String key;
	final String kk;
	
	int index;
	
	public ExtensionNamespacedKey(MiscExtension ext, String key) {
		this.ext = ext;
		this.key = key; // TODO if we don't touch to this, we'll remove it
		this.kk = (this.ext.getInfo().name.replaceAll(" ", "-") + ":" + this.key).toLowerCase();
		
		this.index = -1;
	}
	
	/**
	 * Return the unnamespaced key (the string after the ":")
	 * @return
	 */
	public String getUnamespacedKey() {
		return key;
	}
	
	/**
	 * Bind index to this key. -1 for none. It's better to not touching to this...
	 * @param i
	 */
	public void bindIndex(int i) {this.index = i;}
	/**
	 * Get index of this key. -1 for unbinded
	 * @return
	 */
	public int getIndex() {return index;}
	
	/**
	 * Return the actual key for data storage and other stuffs
	 */
	@Override
	public String toString() {
		return kk;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof String) return kk.equalsIgnoreCase((String) obj);
		else if (obj instanceof ExtensionNamespacedKey) return ((ExtensionNamespacedKey) obj).kk.equalsIgnoreCase(kk);
		else return false;
	}
	
}
