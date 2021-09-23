package sd2.project.events;

import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

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

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        JsonObject data = dataUtils.packageData(p, e);
        dataUtils.writeToFile(data);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e)
    {
        // Melee damage
        if (e.getDamager() instanceof Player)
        {
            Player p = (Player) e.getDamager();
            
            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("damaged", e.getEntityType().toString());
            data.addProperty("cause", e.getCause().toString());
            data.addProperty("weapon", p.getInventory().getItemInMainHand().getType().toString());

            dataUtils.writeToFile(data);
        }
    }

    @EventHandler
    public void onProjHit(ProjectileHitEvent e)
    {
        if (e.getEntity() instanceof Arrow && e.getEntity().getShooter() instanceof Player)
        {
            Player p = (Player)e.getEntity().getShooter();

            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("target", e.getEntityType().toString());
            data.addProperty("weapon", "BOW");

            dataUtils.writeToFile(data);
        }

        if (e.getEntity() instanceof Trident && e.getEntity().getShooter() instanceof Player)
        {
            Player p = (Player)e.getEntity().getShooter();

            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("target", e.getEntityType().toString());
            data.addProperty("weapon", "TRIDENT");

            dataUtils.writeToFile(data);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data);
    }
}
