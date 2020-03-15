package me.nahkd.misc.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nahkd.misc.core.nahkdMisc;
import me.nahkd.misc.core.internal.test.TestUI;

public class DebugTestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				sender.sendMessage("§7>> §bTest command");
				sender.sendMessage("§3inventory §bShow example inventory");
				sender.sendMessage("§3guide §bOpen guide");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("inventory")) {
					nahkdMisc.getData(player).openUI(new TestUI());
					player.sendMessage("§7>> §aOpened test UI");
				} else if (args[0].equalsIgnoreCase("guide")) {
					nahkdMisc.getData(player).openGuide();
					player.sendMessage("§7>> §aOpened guide");
				}
			}
		} else sender.sendMessage("§cno you can only use this as player");
		return true;
	}

}
