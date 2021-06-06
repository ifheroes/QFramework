package eu.hikarion.utils.quest;

public class Quest {
	
	private String npcname;
	private final QuestType type;

	//TODO if proximity == 0 and/or message_proximity == null | proximity chat deactivated for npc
	private int proximity = 0;
	private String message_proximity;
	
	//TODO if message == null | rightclick message deactivated for npc (but guid will still trigger)
	private String message;

	/*
	 * TODO
	 * - filesystem stuff to actually save quests after reloads and restarts
	 * - warn in questlist[/questadmin list or /qa list] if npc has no target! (npcname)
	 * 
	 * 
	 * 
	 */

	
	public Quest(QuestType type) {
		this.type = type;
	}
	public String getNPCName() {
		return npcname;
	}
	//allows setting a different npc name as target.
	public void setNPCName(String npcname) {
		this.npcname = npcname;
	}
	//TYPE CANNOT BE CHANGED AFTERWARD BECAUSE OF CUSTOM PROPERTIES
	public QuestType getType() {
		return type;
	}
}
