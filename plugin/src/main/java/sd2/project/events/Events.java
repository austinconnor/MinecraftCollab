package sd2.project.events;

import com.google.gson.JsonObject;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import sd2.project.utils.DataUtils;

public class Events implements Listener
{
    DataUtils dataUtils = new DataUtils();
    
    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        Location fromLoc = e.getFrom();
        Location toLoc = e.getTo();
        int fromX, fromY, fromZ;
        int toX, toY, toZ;

        // Gather necessary information. In Minecraft, the coordinate of a player
        // is a double, but if we use getBlockX, we chop it down into an int by taking the floor.
        // Note: the from location is from the previous step you took. If I was in block 35 before I moved to block 36,
        // fromX = 35 and toX = 36 
        fromX = fromLoc.getBlockX();
        fromY = fromLoc.getBlockY();
        fromZ = fromLoc.getBlockZ();

        toX = toLoc.getBlockX();
        toY = toLoc.getBlockY();
        toZ = toLoc.getBlockZ();

        // If we get the block we just came from, and compare it to the new block
        if(fromX != toX || fromY != toY || fromZ != toZ)
        {
            JsonObject data = dataUtils.packageData(player, e);

            dataUtils.writeToFile(data);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("block", e.getBlock().getType().name());
        
        dataUtils.writeToFile(data);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("block", e.getBlock().getType().name());
        
        dataUtils.writeToFile(data);
    }
}
