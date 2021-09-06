package me.warpromo.bridging;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;





public class ClickEvent implements Listener {
	
	private Main plugin;
	
	public ClickEvent(Main plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void placeBlock(BlockPlaceEvent e) {
		
		
		HashMap<String, Boolean> recording = plugin.recording;
		
		System.out.println("Checking if Player in recording");
		
		if(recording.containsKey(e.getPlayer().getName()) == false) return;
		
		System.out.println("Recording contains Player");
		
		ArrayList<Block> blocks = plugin.placedBlocks.get(e.getPlayer().getName());
		blocks.add(e.getBlock());
		plugin.placedBlocks.replace(e.getPlayer().getName(), blocks);
		
		System.out.println(e.getBlock().getType());
	}

		
}




	