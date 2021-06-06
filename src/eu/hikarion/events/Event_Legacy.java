package eu.hikarion.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class Event_Legacy {
	public static void onAtEntityInteract(PlayerInteractAtEntityEvent e) {
		
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();
		
		p.sendMessage("[PlayerInteractAtEntityEvent]"+p.getName()+" clicked at entity "+en.getName()+" with id: "+en.getEntityId());
		
	}
	public static void onEntityInteract(PlayerInteractEntityEvent e) {
		
		Player p = e.getPlayer();
		Entity en = e.getRightClicked();
		
		p.sendMessage("[PlayerInteractEntityEvent]"+p.getName()+" clicked at entity "+en.getName()+" with id: "+en.getEntityId());
		
	}
}
