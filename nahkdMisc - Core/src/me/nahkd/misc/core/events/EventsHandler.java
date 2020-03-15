package me.nahkd.misc.core.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import org.bukkit.event.Event;

public interface EventsHandler {
	
	public HashMap<Class<? extends Event>, ArrayList<Consumer<? extends Event>>> getHandlers();
	
}
