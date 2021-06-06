package eu.hikarion.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.hikarion.utils.Tablist;
import eu.hikarion.utils.Utils;
import eu.hikarion.utils.npc.NPC;
import eu.hikarion.utils.npc.PacketReader;

public class Event_PlayerJoin {
	public static void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(NPC.getNPCs() != null||!NPC.getNPCs().isEmpty()) {
			NPC.spawnNPC_OnJoin(p);
		}
		Utils.changeSkin(p);
		PacketReader rd = new PacketReader();
		rd.inject(p);
		
		Tablist.loadTablist(p);
	}
}
