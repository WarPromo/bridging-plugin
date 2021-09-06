package me.warpromo.bridging;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import me.warpromo.bridging.ClickEvent;
import me.warpromo.bridging.Addlore;


public class Main extends JavaPlugin{
	
	public HashMap<String, Location> respawnLoc = new HashMap<String, Location>();
	public HashMap<String, ArrayList<Block>> placedBlocks = new HashMap<String, ArrayList<Block>>();
	public HashMap<String, Double> respawnPoint = new HashMap<String, Double>();
	public HashMap<String, Boolean> recording = new HashMap<String, Boolean>();
	
	@Override
	public void onEnable(){
		
		getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
		this.getCommand("bridge").setExecutor(new Addlore(this));

		
	}
	

	
}