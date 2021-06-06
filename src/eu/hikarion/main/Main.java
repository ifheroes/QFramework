package eu.hikarion.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.hikarion.commands.Cmd_DEBUG;
import eu.hikarion.commands.Cmd_NPC;
import eu.hikarion.events.EventLoader;
import eu.hikarion.utils.npc.PacketReader;

public class Main extends JavaPlugin{

	public static Main instance;

	String prefix = "[Hika] ";
	@Override
	public void onEnable() {
		instance = this;
		System.out.println(prefix+"Engine Started. vrmmmm... vrmmmm!!!");
		
		loadCommands();
		
		new EventLoader(this);
		//HeartBeat.tick(20);
		if(!Bukkit.getOnlinePlayers().isEmpty()) {
			for (Player p:Bukkit.getOnlinePlayers()) {
				new PacketReader().inject(p);
			}
		}
	}

	@Override
	public void onDisable() {
		for (Player p:Bukkit.getOnlinePlayers()) {
			new PacketReader().uninject(p);
		}
	}
	
	private void loadCommands() {
		Cmd_NPC npc = new Cmd_NPC(this);
		getCommand("npc").setExecutor(npc);
		Cmd_DEBUG debug = new Cmd_DEBUG(this);
		getCommand("debug").setExecutor(debug);
		
	}
}
