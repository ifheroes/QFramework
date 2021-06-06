package eu.hikarion.events;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.hikarion.utils.npc.PacketReader;

public class Event_PlayerQuit {
	public static void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		PacketReader rd = new PacketReader();
		rd.uninject(p);
	}
}
