package sd2.project.events;

import com.google.gson.JsonObject;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;

import io.papermc.paper.event.player.AsyncChatEvent;
import sd2.project.utils.ChatDataUtils;
import sd2.project.utils.DataUtils;

// A full list of all player events can be found here:
// https://papermc.io/javadocs/paper/1.17/com/destroystokyo/paper/event/player/package-summary.html

public class Events implements Listener
{
    DataUtils dataUtils = new DataUtils();
    ChatDataUtils chatDataUtils = new ChatDataUtils();
    
    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        Location fromLoc = e.getFrom();
        Location toLoc = e.getTo();
        int fromX, fromY, fromZ;
        int toX, toY, toZ;

        // We don't want to collect data from admins that are in creative mode for specific reasons.
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
            return;

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

            dataUtils.writeToFile(data, dataUtils.outputFileName);

        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        // We don't want to collect data from admins that are in creative mode for specific reasons.
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
            return;

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("block", e.getBlock().getType().toString());
        
        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();

        // We don't want to collect data from admins that are in creative mode for specific reasons.
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
            return;

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("block", e.getBlockPlaced().getType().toString());
        
        dataUtils.writeToFile(data, dataUtils.outputFileName);

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

            dataUtils.writeToFile(data, dataUtils.outputFileName);
        }
    }

    @EventHandler
    public void onProjHit(ProjectileHitEvent e)
    {
        if (e.getEntity() instanceof Arrow && e.getEntity().getShooter() instanceof Player && e.getHitEntity() != null)
        {
            Player p = (Player)e.getEntity().getShooter();

            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("target", e.getHitEntity().getType().toString());
            data.addProperty("weapon", e.getEntity().getType().toString());

            dataUtils.writeToFile(data, dataUtils.outputFileName);
        }

        if (e.getEntity() instanceof Trident && e.getEntity().getShooter() instanceof Player && e.getHitEntity() != null)
        {
            Player p = (Player)e.getEntity().getShooter();

            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("target", e.getHitEntity().getType().toString());
            data.addProperty("weapon", e.getEntity().getType().toString());

            dataUtils.writeToFile(data, dataUtils.outputFileName);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        Player p = e.getEntity();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onEnterPortal(PlayerChangedWorldEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        data.addProperty("oldWorld", e.getFrom().getName());

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onEnchantment(EnchantItemEvent e)
    {
        Player p = e.getEnchanter();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("itemDrop", e.getItemDrop().getName());

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onPlayerLeash(PlayerLeashEntityEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("entity", e.getEntity().getName());

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onPlayerUnleash(PlayerUnleashEntityEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("entity", e.getEntity().getName());

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e)
    {
        if(e.isHatching())
        {
            Player p = e.getPlayer();
            
            JsonObject data = dataUtils.packageData(p, e);
            data.addProperty("hatched", true);

            dataUtils.writeToFile(data, dataUtils.outputFileName);
        }
    }

    @EventHandler
    public void onShear(PlayerShearEntityEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);

        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onBucketPlace(PlayerBucketEmptyEvent e)
    {
        Player p = e.getPlayer();

        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("contents", e.getBucket().toString());

        
        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }
    
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e)
    {
        Player p = e.getPlayer();
        
        JsonObject data = dataUtils.packageData(p, e);
        data.addProperty("contents", e.getBlockClicked().getType().toString());
        
        dataUtils.writeToFile(data, dataUtils.outputFileName);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        // We don't want to collect data from admins that are in creative mode for specific reasons.
        if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
            return;

        JsonObject data = dataUtils.packageData(p, e);

        data.addProperty("itemHeld", p.getInventory().getItemInMainHand().getType().toString());
        data.addProperty("action", e.getAction().toString());

        dataUtils.writeToFile(data, dataUtils.outputFileName);   
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent e) 
    {
        JsonObject data = chatDataUtils.packageChatData(e.getPlayer(), e);

        dataUtils.writeToFile(data, chatDataUtils.outputFileName);
    }
}
