package eu.hikarion.utils.npc;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.hikarion.main.Main;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.MinecraftServer;
import net.minecraft.server.v1_16_R2.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_16_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_16_R2.PlayerConnection;
import net.minecraft.server.v1_16_R2.PlayerInteractManager;
import net.minecraft.server.v1_16_R2.WorldServer;

public class NPC {
	
	private static List<EntityPlayer> NPC = new ArrayList<EntityPlayer>();
	
	public static List<EntityPlayer> getNPCs(){
		return NPC;
	}
	public static void createNPC(Location loc,String name) {
		MinecraftServer nmsSrv = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWrld = ((CraftWorld)loc.getWorld()).getHandle();
		GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), "§a"+name);
		EntityPlayer npc = new EntityPlayer(nmsSrv, nmsWrld, fakeProfile,new PlayerInteractManager(nmsWrld));

		npc.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());

		
		spawnNPC(npc);
		
		NPC.add(npc);
		
	}
	public static void createNPC(Location loc,String nname,String skin) {
		MinecraftServer nmsSrv = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWrld = ((CraftWorld)loc.getWorld()).getHandle();
		GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), "§a"+nname);
		EntityPlayer npc = new EntityPlayer(nmsSrv, nmsWrld, fakeProfile,new PlayerInteractManager(nmsWrld));

		npc.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
		
		String[] name = getSkin(skin);
		
		fakeProfile.getProperties().put("textures",new Property("textures", name[0], name[1]));
		
		spawnNPC(npc);
		NPC.add(npc);
		
	}
	public static void updatePlayerMovement(EntityPlayer npc) {
		for(Player observer:Bukkit.getOnlinePlayers()) {
			PlayerConnection connection = ((CraftPlayer) observer).getHandle().playerConnection;
			connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
		
	}
	public static void spawnNPC(Player observer, EntityPlayer npc) {
		
		PlayerConnection connection = ((CraftPlayer) observer).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER,npc));
		//spawns the NPC
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
		connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		
		Bukkit.getScheduler().runTaskLater(Main.instance, new Runnable() {
			@Override
			public void run() {connection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER,npc));}
		}, 30);
	}
	private static void spawnNPC(EntityPlayer npc) {
		for(Player observer:Bukkit.getOnlinePlayers()) {
			spawnNPC(observer, npc);
		}
	}
	public static void spawnNPC_OnJoin(Player p) {
		for(EntityPlayer npc:NPC) {
			spawnNPC(p,npc);
		}
	}
	private static String[] getSkin(String name) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();
			
			URL url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid+"?unsigned=false");
			InputStreamReader reader2 = new InputStreamReader(url2.openStream());
			
			JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
			
			String texture = property.get("value").getAsString();
			String signature = property.get("signature").getAsString();
			return new String[] {texture,signature};
			
		} catch (Exception e) {
			GameProfile profile = new GameProfile(UUID.fromString("53bf3b03-533b-44ee-aa84-fbffa855b4d8"), "§a"+name);
			//EntityPlayer pl = ((CraftPlayer) p).getHandle();
			//GameProfile profile = pl.getProfile();
			Property property = profile.getProperties().get("textures").iterator().next();
			String texture = property.getValue();
			String signature = property.getSignature();
			return new String[] {texture,signature};
		}
	}
}
