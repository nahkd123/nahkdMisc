package me.nahkd.misc.core.events;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

public interface ItemEventsHandler extends EventsHandler {
	
	default public void addInteractHandlers(Consumer<PlayerInteractEvent> event) {
		if (!getHandlers().containsKey(PlayerInteractEvent.class)) getHandlers().put(PlayerInteractEvent.class, new ArrayList<Consumer<? extends Event>>());
		getHandlers().get(PlayerInteractEvent.class).add(event);
	}
	
}
