package eu.hikarion.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import eu.hikarion.main.Main;
import eu.hikarion.utils.Tablist;
import eu.hikarion.utils.Utils;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R2.PacketPlayOutRespawn;
import net.minecraft.server.v1_16_R2.PlayerConnection;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class Cmd_DEBUG implements CommandExecutor{
	
	public static ArrayList<String> debug_list = new ArrayList<>();
	
	public Cmd_DEBUG(Main main) {
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			String uuid = p.getUniqueId().toString();
			
			if(debug_list.contains(uuid)){
				p.sendMessage("§e§lDebug mode deactivated!");
				debug_list.remove(uuid);
				PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
				connection.sendPacket(new PacketPlayOutRespawn());
				Utils.changeSkin(p,"noob");
				Utils.updateSkin(p);
				return true;
			}else {
				p.sendMessage("§a§lDebug mode activated!");
				debug_list.add(uuid);
				Utils.changeSkin(p,"knight");
				Utils.updateSkin(p);
				return true;
			}
		}
		return false;
	}

}
