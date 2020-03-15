package me.nahkd.misc.core;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nahkd.misc.core.ext.ExtensionInfo;
import me.nahkd.misc.core.ext.MiscExtension;
import me.nahkd.misc.core.services.RegistryService;
import me.nahkd.misc.core.temporary.PlayerTemporaryData;

public class nahkdMisc {
	
	private static CommandSender primaryLogger;
	private static HashMap<Player, PlayerTemporaryData> tempPdata;
	public static void setLogger(CommandSender sender) {
		primaryLogger = sender;
	}
	
	/**
	 * Load extension. The extension must load once.
	 * @param ext
	 */
	public static void loadExtension(MiscExtension ext) {
		ExtensionInfo inf = ext.getInfo();
		ext.registerAll(RegistryService.getService());
		primaryLogger.sendMessage("§3[Extension] §bLoaded " + inf.name + " by " + inf.author + "...");
	}
	public static HashMap<Player, PlayerTemporaryData> getPlayerDataMap() {
		if (tempPdata == null) tempPdata = new HashMap<Player, PlayerTemporaryData>();
		return tempPdata;
	}
	public static PlayerTemporaryData getData(Player p) {
		PlayerTemporaryData dat;
		if (!getPlayerDataMap().containsKey(p)) getPlayerDataMap().put(p, dat = new PlayerTemporaryData(p));
		else return getPlayerDataMap().get(p);
		return dat;
	}
	
	public static void imDoneWithMyLife() {
		primaryLogger = null;
		if (tempPdata.size() > 0) tempPdata.clear();
		tempPdata = null;
	}
	
}
