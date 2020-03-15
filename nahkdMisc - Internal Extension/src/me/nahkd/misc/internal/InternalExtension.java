package me.nahkd.misc.internal;

import me.nahkd.misc.core.ext.ExtensionInfo;
import me.nahkd.misc.core.ext.MiscExtension;
import me.nahkd.misc.core.registry.Registry;
import me.nahkd.misc.internal.categories.basicmachines.AdvancedWorkbench;
import me.nahkd.misc.internal.categories.basicmachines.BasicMachines;
import me.nahkd.misc.internal.recipes.AdvancedWorkbenchRecipes;

public class InternalExtension implements MiscExtension {

	@Override
	public ExtensionInfo getInfo() {
		return new ExtensionInfo("nahkdMisc", "nahkd123");
	}

	@Override
	public void preRegister() {
	}

	@Override
	public void registerAll(Registry reg) {
		new AdvancedWorkbenchRecipes();
		
		BasicMachines BASIC_MACHINES = new BasicMachines();
		reg.register(BASIC_MACHINES);
		reg.register(new AdvancedWorkbench());
	}

}
