package eu.hikarion.utils.quest;

public enum QuestType {
	VENDOR, //OPENS A SHOP GUI
	TRADE, //
	GATHER, //You need to gather specified items that only exist for the quest (normal items with specific names)
	@Deprecated KILL, //UNUSED You need to kill a specific enemy (
}
