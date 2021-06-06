package eu.hikarion.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender.Spigot;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.hikarion.main.Main;
import net.minecraft.server.v1_16_R2.DimensionManager;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.EnumGamemode;
import net.minecraft.server.v1_16_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_16_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R2.PlayerConnection;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_16_R2.PacketPlayOutRespawn;

public class Utils {
	public static void changeSkin(Player p) {
		GameProfile profile = ((CraftPlayer)p).getProfile();
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		
		con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,((CraftPlayer)p).getHandle()));
		
		profile.getProperties().removeAll("textures");
		profile.getProperties().put("textures",getSkin("noob"));
		
		con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,((CraftPlayer)p).getHandle()));
	}
	public static void changeSkin(Player p,String skin) {
		GameProfile profile = ((CraftPlayer)p).getProfile();
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		
		con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,((CraftPlayer)p).getHandle()));
		
		profile.getProperties().removeAll("textures");
		profile.getProperties().put("textures",getSkin(skin));
		
		con.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,((CraftPlayer)p).getHandle()));
	}
	public static Property getSkin(String id) {
		switch (id) {
		case "knight": return new Property("textures", "eyJ0aW1lc3RhbXAiOjE1NzU5MDY3MjkzNTMsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80NjEyNmE2Zjk5MmFlODhiNjAzM2JjMjEyY2ExMTJmZjNkZDNmNzI3ZWFhN2VkOTFjNzU0ZTY5NTViMDFjYjUwIn19fQ==", "Pqdkx0d0pCmB5g5fGgOh3IId9eVgsI7xDJXxc3fWvYAIPL6CzFmhUkx2BHTflczoNSGcX7pEJ4zpbZHdhFyTZGyN+wgaHwQOZ6o1TCwFDmFqpTkYqB6TIquut+Z+cfrjlD96CK89z3Iaq8i+yAP+ADRZ6VFHvQJHPv5Q7nPy8ZxzCnVID5cRbFuwM5dJjMqnEeRymISM8srKg/U2AupSE8xcxXzhb8okG9hzLVYT40Ax5qbDcATo4RONdsDvQa3msW42nIxNcGANUSDLOlJbSnlokN+/k5wYW1CVpfSYnZQ3JDTwOQUwYHv1KPDjGRLLWoJsOJ5q3sqPeZYKeN9LmMc61OWXhkUupqLmjw5VzHcPc1m9ohZ7bHDoLfsYgGSWu2wkNvC4UW4ZIR9Atkusg4IK6VV+xB8ay1/Lvbi5W6rMs91c2sOxt1g2aM6JnubmqKiSqgaylQt6G0dpYcl0LyaGhm7+WFdmhc8ps2OopBFLbtTJJ+rp513SwH9mYYSuUul2gi8gKsY/N3r2Ocu2MPJdyAFBO/AcCRAY/e9bR67tq8zkFZg7a6FUz2cLZZpIpeQDvFGOXKQ7SbQ55g30pe3s05gxYobccgYf5TnMokTAnlZ4iyFkOdRsx46ExJbJCYa8TxDu2WW9e3Bh31Ags4MGJPqpWq9Du+ptYhRKClo=");
		case "noob": return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTU5MTcwNjYzMzU5OSwKICAicHJvZmlsZUlkIiA6ICIxOTI1MjFiNGVmZGI0MjVjODkzMWYwMmE4NDk2ZTExYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTZXJpYWxpemFibGUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTYyZWM1YmRiNDc5YTMzZTY0ZjE2ZjVjM2JiNzFiZWRlYmM1NmI2NjZhODAzM2E0MmY3NGM0NTEzZTYyN2JhYSIKICAgIH0KICB9Cn0=", "J7mSQExDoMcRQCi+bibGvHvztrKHb3iUrqeJFAXJKbm/HIQfcbz7jj1JGNzf/N0TS+XtCIPCQa0GuJggCPTWAatNqQII9VjaOI1SLgte7J159BXERQt/Xmxs3cmBcchuqahWhpue69x9gC70stavHjxTgCn9kGN2YOciN9GaKHydHhMLueCN7j8ZJhnunQWvf4u6i5rpVPFiG0U5uiim5YHSDqM5ce9gzqaDBIBv8fp3DB5Vm4DZA+dCXQkwrsXxhie/WkSch2kuy/RxqY7Iw3uRhee+6KKrVkyUVjs0/1B3H0ASc5MIj7MhB24j0jX9tUW7yLMQeLlzH+PWJNoPk6z6CKn+7tJ63h1t39shSeA1Sx0GR71XfCNAGrjq/9vlZolu1kzulk3VS5sbpamlNqbmQO4P4efLhVoWj0LKK/6lyeORu69ccIW26Ot7c5S5DxX6auYfaZlzPY6EK4AlPEA1U5cDGH9Vyt362WWOrcxjF3Q0kLH8ZD27fJb3WQXvKXO9oNrsdNNjLnCvGeJ8AXjlweyx8UeWXR30FZ4F7HIk7EkpSMSAjnh9o3L2/eIb+l4SWUYpN/WKYHZZc6OKFNqgzPl2ixmDEpIYp7awNKhF4zyrI7VLwHPBSbMGKArx2X+7Sr3POe7SavX6xyr+CKvIDgR03gVHDuT2vwyUjfM=");
		default: return new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYxNjQ4ODQzODQ0NiwKICAicHJvZmlsZUlkIiA6ICIwZGVlZGQ4ZTc2MGQ0ZTNmYjY0MjJhYzdkYzZlNTE4YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJFbGZpZ2FtZXIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdmNjRiYzI1NTNjZWVkMWMzZjc3NzBiMzM4YWUxODU1MmE3YmYzNzQ3MjMxNzNjMDNmYTJhYzU5NzBiN2NkMiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "cegG6SMnbVZjRDDgVWrg0aRww5Gm3kJf7dI4q2xo9gMuBMvNdXNzdFWS8W5/1KTzn4ZFE5oewmZKFQ9UXELJMLLk+boxVnreVjJ1oD75sziKJW0Z5Zaz6i5tDD/nYZP1yrpIlhGwWH0Y477AIafmNkDcWXaAISNZByDcsRZe9qFSePAmv9C6/SmcfF55UVWQcOnuOixAfmR+tPCk+VNnl97lYn1qdrKfdRuCPN/5ktR8lGLZ4BzDV538s9luniHwPn3H3ye74ozX641/zrYoDU0AepP9SThqSWIYHTtTNZd0c7Y45YwasS6qN7+4yoQh6vOyxQiCZiyr5fUEa3ymmIDmbuIgwarsPgoy88QA+rQZy05r3eKnkOZbOaDq9BB5R32dUEToNtYnZ4Vsrh21XbvPrNZSXfHgQq46P1KnKq5WxSygVbMNaTdSNqq5bp2AcffKg+Hlz9kyxjBLHjQCMnzewCNIIR/uofcAXlG2MxXb3tKmqcjBKvcn/n2V/TdMm7stZHSjk0umPGjoC6uK2CIUY9lRhYHBmPXV54acDLgQkl8G/3dfIgR1Az2x7K3PtJAO5gWrOeEGtwNw3KoH6badyeQji5/my/PmNZSCR2M/dV5DfmJNsqpjv+aacPX61/MP10K0zJLXXy5/7HV5pA6KqrSmX/LlWmkrmD7Q9Cc=");
		}		
	}
	//@SuppressWarnings("deprecation")
	public static void updateSkin(Player p) {

		p.getLocation().getChunk().unload(true);
		p.getLocation().getChunk().load();
		
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		//con.sendPacket(new PacketPlayOutEntityDestroy(p.getEntityId()));
		//con.sendPacket(new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle()));
		
		Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
			@Override
			public void run() {
				/*
				for(Player all:Bukkit.getOnlinePlayers()) {
					
					if(!all.getName().equals(p.getName())) {
						((CraftPlayer)all).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle()));
					}
				}
				*/
			}
		}, 5);
	}
}
