package me.warpromo.bridging;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;




public class Addlore implements CommandExecutor {

    // This method is called, when somebody uses our command
	private Main plugin;
	
	
	public Addlore(Main plugin) {
		this.plugin = plugin;
	}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	if(args.length == 0) return false;
    	
    	String arg = args[0];
    	Player p = (Player) sender;
    	
    	if(arg.contentEquals("setpos")) {
    		
    		p.sendMessage("Beginning Bridge");
    		
    		if(plugin.respawnLoc.containsKey(p.getName())) {
    			plugin.respawnPoint.remove(p.getName());
    			plugin.placedBlocks.remove(p.getName());
    		}
    		
    		plugin.placedBlocks.put(p.getName(), new ArrayList<Block>());
    		plugin.respawnLoc.put(p.getName(), p.getLocation());
    		plugin.respawnPoint.put(p.getName(), 0.4d);
    		plugin.recording.put(p.getName(), true);
    		
    		new BukkitRunnable() {
    			
    			int count = 0;
    			
    			public void run() {
    				
    				
    				if(plugin.recording.containsKey(p.getName()) == false) this.cancel();
    				
    				Location l = plugin.respawnLoc.get(p.getName());
    				Location l2 = p.getLocation();
    				double d = plugin.respawnPoint.get(p.getName());
    				ArrayList<Block> blocks = plugin.placedBlocks.get(p.getName());
    				
    				System.out.println(blocks.size());
    				
    				if(blocks.size() > 0) count++;
    				
    				if(p.getLocation().getY() < l.getY() - d) {
    					
    					
    					int size = blocks.size();
    					
    					System.out.println("REMOVING BLOCKS: " + size);
    					
    					for(int a = 0; a < size; a++) {
    						blocks.get(a).setType(Material.AIR);
    					}
    					
    					double dist = Math.sqrt( Math.pow( (l.getX() - l2.getX()) , 2 ) + Math.pow( (l.getZ() - l2.getZ()) , 2 ) );
    					
    					if(size > 5) p.sendMessage( size + " blocks" ); 
    					count = 0;
    					
    					blocks.clear();
    					
    					plugin.placedBlocks.replace(p.getName(), blocks );
    					

    					p.teleport(l);
    					p.setVelocity(new Vector(0,0,0));
    					
    				}
    				
    				
    			}
    		}.runTaskTimer(plugin, 0L, 0L);
    	}
    	
    	return false;
    
    }
  
}