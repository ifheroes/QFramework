package eu.hikarion.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Tablist {
	public static void loadTablist(Player p){
		Scoreboard sb1 = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb1.getObjective("xxx") != null ? sb1.getObjective("yyy") : sb1.registerNewObjective("xxx", "yyy","zzz");
		
		String name = p.getName();
		
		sb1.registerNewTeam("a");
        Team team = sb1.getTeam("Admin");
        Team team1 = sb1.getTeam("Badmin");
        if(team1 == null) {
            team1 = sb1.registerNewTeam("Badmin");
        }
        if(team == null) {
            team = sb1.registerNewTeam("Admin");
        }
        team.setPrefix("Badmin");
        team.setSuffix("a");
        
        team1.setPrefix("Admin");
        team1.setSuffix("a");
        
        team.addEntry("AkabeX86");
        team1.addEntry("Luke_LT");
	        
		p.setScoreboard(sb1);
	}
}
