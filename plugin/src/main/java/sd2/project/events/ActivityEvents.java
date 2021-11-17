package sd2.project.events;

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
import sd2.project.utils.InventoryUtils;

public class ActivityEvents implements Listener
{
    int cd = 5;

    public Main plugin;

    public ActivityEvents(Main instance)
    {
        plugin = instance;
    }

    InventoryUtils invUtils = new InventoryUtils();

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
                ItemStack currentItem = e.getCurrentItem();

                if (currentItem.getItemMeta() == null || currentItem.getItemMeta().displayName() == null)
                    return;

                for (String s : actions)
                {
                    if (currentItem.getItemMeta().displayName().equals(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + s)))
                    {
                        p.closeInventory();

                        Inventory mineInv = invUtils.createActivityInventory(p, currentItem.getItemMeta().displayName());
                        
                        p.openInventory(mineInv);

                        // REPEATING TASK TO FILL NEARBY PLAYER HEADS HERE
                        // Sort by closest to farthest (?)

                        // Slots:
                        // 10 - 16
                        // 19 - 25
                        // 28 - 34
                        // 37 - 43

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
                            
                        }.runTaskTimer(plugin, 0, 20);
                    }
                    break;
                }
            }
        }
    }
}
