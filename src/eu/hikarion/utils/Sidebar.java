package eu.hikarion.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Sidebar {
	public static void loadSidebar(Player p){
		Scoreboard sb1 = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = sb1.getObjective("xxx") != null ? sb1.getObjective("yyy") : sb1.registerNewObjective("xxx", "yyy","zzz");
		loadSidebarContents(p, obj);
		loadTablist(p, sb1);
		p.setScoreboard(sb1);
	}
	private static void loadSidebarContents(Player p,Objective obj){
		
		obj.setDisplayName("ThisIsASidebar");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.getScore("§2This").setScore(15);
		obj.getScore("§3IS").setScore(14);
		obj.getScore("§4a.").setScore(13);
		obj.getScore("§5SiDeBaR").setScore(12);
	}
	private static void loadTablist(Player p,Scoreboard sb1) {
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
	}
}
