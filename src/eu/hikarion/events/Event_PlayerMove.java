package eu.hikarion.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import eu.hikarion.utils.npc.NPC;
import net.minecraft.server.v1_16_R2.EntityPlayer;

public class Event_PlayerMove {
	public static void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for(EntityPlayer npc:NPC.getNPCs()) {
			if(p.getLocation().distanceSquared(((Entity) npc).getLocation()) <= 4 * 4){
				p.sendMessage("NPC IS NEARBY");
				NPC.updatePlayerMovement(npc);
			}
		}
	}
}
