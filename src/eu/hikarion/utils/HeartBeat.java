package eu.hikarion.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import eu.hikarion.main.Main;
import eu.hikarion.utils.npc.NPC;

public class HeartBeat {
	public static void tick(long spd) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			
			@Override
			public void run() {
				Bukkit.broadcastMessage("[INFO] Players:"+Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers()+" NPCs:"+NPC.getNPCs().size());
				for(Entity e:Bukkit.getWorld("spawn").getEntities()) {
					if(e.getName().equalsIgnoreCase("player"))Bukkit.broadcastMessage("- ID:"+e.getEntityId()+" Name:"+e.getName()+" CustomName:"+e.getCustomName()+"");
				}
			}
		}, spd, spd);
	}
}
