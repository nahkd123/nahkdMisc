package me.nahkd.misc.core.services;

import java.util.HashMap;

public abstract class Service {
	
	public Service() {
		svs.put(this.getClass(), this);
	}
	public abstract void configure(String setting, Object val);
	public void shutdownService() {
		onShutdown();
		// svs.remove(this.getClass()); ConcurrentModification
	}
	public abstract void onShutdown();
	
	private static HashMap<Class<? extends Service>, Service> svs;
	public static Service getService(Class<? extends Service> clazz) {
		return svs.get(clazz);
	}
	
	public static void setup() {
		svs = new HashMap<Class<? extends Service>, Service>();
	}
	public static void shutdownAll() {
		for (Service sv : svs.values()) sv.shutdownService();
		svs.clear();
	}
	
}
