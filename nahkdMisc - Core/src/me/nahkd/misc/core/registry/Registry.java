package me.nahkd.misc.core.registry;

public interface Registry {
	
	public void register(Registerable registerable);
	default void registers(Registerable... objs) {
		for (Registerable reg : objs) register(reg);
	}
	
}
