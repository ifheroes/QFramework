package eu.hikarion.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.hikarion.main.Main;
import eu.hikarion.utils.npc.PlayerInteractNpcEvent;

public class EventLoader implements Listener{
	public EventLoader(Main plugin){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler public void onPlayerJoin(PlayerJoinEvent e){Event_PlayerJoin.onPlayerJoin(e);}
	@EventHandler public void onPlayerQuit(PlayerQuitEvent e){Event_PlayerQuit.onPlayerQuit(e);}
	@EventHandler public void onPlayerInteractNpc(PlayerInteractNpcEvent e){Event_PlayerInteractNpc.onPlayerInteractNpc(e);}
	
	//LEGACY
	@EventHandler public void onAtEntityInteract(PlayerInteractAtEntityEvent e){Event_Legacy.onAtEntityInteract(e);}
	@EventHandler public void onEntityInteract(PlayerInteractEntityEvent e){Event_Legacy.onEntityInteract(e);}
	
}
