package sd2.project.events;

import java.util.Map;
import java.util.TreeMap;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.kyori.adventure.text.Component;
import sd2.project.Main;
import sd2.project.utils.DataUtils;
import sd2.project.utils.InventoryUtils;

public class ActivityEvents implements Listener
{
    // Activity Cooldown
    int cd = 180;

    public Main plugin;

    public ActivityEvents(Main instance)
    {
        plugin = instance;
    }

    InventoryUtils invUtils = new InventoryUtils();
    DataUtils dataUtils = new DataUtils();

    String [] actions = {"Mining", "Exploring", "Building", "Idle"};

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        InventoryView inv = e.getView();

        if (e.getWhoClicked() instanceof  Player)
        {
            if (inv.title().equals(Component.text(Main.prefix + ChatColor.DARK_PURPLE + "What are you doing?")))
            {
                Player p = (Player) e.getWhoClicked();
            
                e.setCancelled(true);

                // Check items and do stuff.
                if (e.getCurrentItem() == null || e.getCurrentItem().getItemFlags() == null || e.getCurrentItem().getItemMeta().displayName() == null)
                    return;

                ItemStack currentItem = e.getCurrentItem();

                for (String s : actions)
                {
                    if (currentItem.getItemMeta().displayName().equals(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + s)))
                    {
                        p.closeInventory();

                        Inventory actInv = invUtils.createActivityInventory(p, currentItem.getItemMeta().displayName());
                        
                        p.openInventory(actInv);

                        // top 10 nearest players
                        int limit = 10;
                        int count = 0;
                        
                        TreeMap<Double, ItemStack> sortedHeads = new TreeMap<>();
                        
                        for (Player near : p.getWorld().getPlayers())
                        {
                            if (near == p)
                                continue;
                            
                            if (count == limit)
                            break;
                            
                            double distance = p.getLocation().distanceSquared(near.getLocation());
                            
                            sortedHeads.put(Math.sqrt(distance), invUtils.getPlayerHead(near, distance));
                            
                            count++;
                        }
                        Main.proximityData.put(p, sortedHeads);

                        
                        
                        int slot = 10;

                        for (Map.Entry<Double,ItemStack> map : sortedHeads.entrySet())
                        {

                            actInv.setItem(slot, map.getValue());


                            // We don't want to use slots 17 and 18.
                            if (slot == 17)
                                slot = 19;

                            slot++;
                        }
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onActionInv(InventoryClickEvent e)
    {
        InventoryView inv = e.getView();

        if (e.getWhoClicked() instanceof Player)
        {
            Player p = (Player) e.getWhoClicked();

            for (String s : actions)
            {
                if (inv.title().equals(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + s)))
                {
                    e.setCancelled(true);

                    if ( e.getCurrentItem() == null)
                        return;

                    ItemStack currentItem = e.getCurrentItem();
                    
                    if (currentItem.getItemMeta() == null || currentItem.getItemMeta().displayName() == null)
                    return;
                    
                    if (currentItem.getItemMeta().displayName().equals(Component.text(ChatColor.RED + "" + ChatColor.BOLD + "CANCEL")))
                    {
                        p.closeInventory();
                        p.openInventory(Main.actInv);
                        return;
                    }

                    if (currentItem.getItemMeta().displayName().equals(Component.text(ChatColor.GREEN + "" + ChatColor.BOLD + "SUBMIT")))
                    {
                        // Write data to file and prepare to send to DB.
                        p.sendMessage(Component.text(Main.prefix + ChatColor.GREEN + "Submission recorded."));
                        p.closeInventory();
                        
                        // Map needs to be the sorted heads TreeMap
                        JsonObject json = dataUtils.packageActionData(p, inv.getTitle().substring(4), Main.proximityData.get(p));

                        dataUtils.writeToFile(json, dataUtils.activityFileName);

                        // Put them into a cooldown
                        Main.cooldown.put(p.getUniqueId(), "act", cd);

                        new BukkitRunnable() 
                        {
                            public void run()
                            {
                                // Reduce cooldown by 1
                                Main.cooldown.put(p.getUniqueId(), "act", Main.cooldown.get(p.getUniqueId(), "act") - 1);

                                if (Main.cooldown.get(p.getUniqueId(), "act") == 0)
                                {
                                    p.sendMessage(Component.text(Main.prefix + ChatColor.GREEN + "You may now use the activity GUI once again."));
                                    Main.cooldown.remove(p.getUniqueId(), "act");
                                    this.cancel();
                                }
                            }
                            
                        }.runTaskTimer(plugin, 20, 20);
                    }
                    break;
                }
            }
        }
    }
}
