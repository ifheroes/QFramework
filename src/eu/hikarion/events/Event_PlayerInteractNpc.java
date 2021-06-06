package eu.hikarion.events;

import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import eu.hikarion.commands.Cmd_DEBUG;
import eu.hikarion.utils.Utils;
import eu.hikarion.utils.npc.PlayerInteractNpcEvent;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_16_R2.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R2.PlayerConnection;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_16_R2.PacketPlayOutRespawn;

public class Event_PlayerInteractNpc {
	public static void onPlayerInteractNpc(PlayerInteractNpcEvent e) {
		Player p = e.getPlayer();
		EntityPlayer npc = e.getNPC();
		String npcName = npc.getName();
		if(Cmd_DEBUG.debug_list.contains(p.getUniqueId().toString())) {
			//show debug information on rightclick if player is in debug mode
			p.sendMessage("§e§lNPC INFO §8§l| §e§l"+npcName);
			p.sendMessage("§8- §7 §e"+" var_BX="+npc.bx+" var_BY="+npc.by+" var_BZ="+npc.bz);
			p.sendMessage("§8- §7 §e var_A="+npc.A);
			p.sendMessage("§8- §7 §e var_aA="+npc.aA);
			p.sendMessage("§8- §7 §e var_aB="+npc.aB);
			p.sendMessage("§8- §7 §e var_aC="+npc.aC);
			p.sendMessage("§8- §7 §e var_aD="+npc.aD);
			p.sendMessage("§8- §7 §e var_aE="+npc.aE);
			p.sendMessage("§8- §7 §e var_ak="+npc.ak);
			p.sendMessage("§8- §7 §e var_am="+npc.am);
			p.sendMessage("§8- §7 §e var_ap="+npc.ap);
			p.sendMessage("§8- §7 §e var_ar="+npc.ar);
			p.sendMessage("§8- §7 §e var_aR="+npc.aR);
			p.sendMessage("§8- §7 §e var_lastX="+npc.lastX);
			p.sendMessage("§8- §7 §e var_lastY="+npc.lastY);
			p.sendMessage("§8- §7 §e var_lastZ="+npc.lastZ);
			p.sendMessage("§asending npc to player location...");
			npc.setPosition(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
			
			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}else {
			//TODO normal schedule
			p.sendMessage("§a<"+npcName+"> toeete miiiich!...");
		}
	}
}
