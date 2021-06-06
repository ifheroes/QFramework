package eu.hikarion.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import eu.hikarion.main.Main;
import eu.hikarion.utils.npc.NPC;

public class Cmd_NPC implements CommandExecutor{
	public Cmd_NPC(Main main) {
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("create")) {
					if(args.length == 3) {
						//SYNTAX npc <name> <skin_name>
						NPC.createNPC(p.getLocation(), args[1], args[2]);
					}else if(args.length == 2) {
						//SYNTAX npc <name>
						NPC.createNPC(p.getLocation(), args[1]);
					}else {
						p.sendMessage("Try /npc create <name> or /npc create <name> <name_of_skin_owner>");
					}
					return true;
				}else {
					//TODO /npc modify <text_interact,text_close> TEXT_INTERACT text will be overwritten if the npc is a quest target!
					p.sendMessage("Try /npc <create,modify> ...");
				}
			}
		}
		return false;
	}

}
