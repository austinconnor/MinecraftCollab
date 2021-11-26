package sd2.project;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import sd2.project.cmds.Activity;
import sd2.project.cmds.ClearData;
import sd2.project.cmds.SendData;
import sd2.project.events.ActivityEvents;
import sd2.project.events.Events;
import sd2.project.utils.ChatDataUtils;
import sd2.project.utils.ComponentUtils;
import sd2.project.utils.DataUtils;
import sd2.project.utils.InventoryUtils;

public class Main extends JavaPlugin
{

    public static MongoClient mongoClient = null;
    public static MongoDatabase database = null;

    int cd = 300;

    public DataUtils dataUtils = new DataUtils();
    public ChatDataUtils chatDataUtils = new ChatDataUtils();
    public ComponentUtils componentUtils = new ComponentUtils();
    public InventoryUtils invUtils = new InventoryUtils();

    public static Table<UUID, String, Integer> cooldown = HashBasedTable.create();

    // Chat prefix [DC] <message>
    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + 
                                        ChatColor.BOLD + "D" + ChatColor.WHITE + ChatColor.BOLD + "C" + 
                                        ChatColor.RESET + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    // Inventories
    public static Inventory actInv = null;

    // Event Classes
    public Activity activity = new Activity(this);


    // The period of time to send data in minutes.
    static final int period = 1;

    public static HashMap<Player, TreeMap<Double, ItemStack>> proximityData = new HashMap<>();

    @Override
    public void onEnable()
    {
        // Undo these comments.
        mongoClient = new MongoClient(new MongoClientURI(dataUtils.dataURI));
        database = mongoClient.getDatabase("Data");

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new ActivityEvents(this), this);
        pm.registerEvents(new Events(), this);
        
        this.getCommand("sendData").setExecutor(new SendData());
        this.getCommand("clearData").setExecutor(new ClearData());
        this.getCommand("act").setExecutor(new Activity(this));   
        this.getCommand("activity").setExecutor(new Activity(this));  
        
        actInv = invUtils.createActivityInventory();
        
        getLogger().info(prefix + ChatColor.RED + "Inventories " + ChatColor.GRAY + "have been loaded.");
        getLogger().info(prefix + ChatColor.RED + "DataCollection " + ChatColor.GRAY + "been enabled.");
        

        // ok so we have to have the data properly go to the database 

        // Send data loop
        new BukkitRunnable()
        {
            public void run()
            {
                // Undo these comments.
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB("worldData", dataUtils.outputFileName) + " PLAYER data documents this time around.");
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB("chatData", chatDataUtils.outputFileName) + " CHAT data documents this time around.");
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB("activityData", dataUtils.activityFileName) + " ACTIVITY data documents this time around.");

                dataUtils.clearFile(dataUtils.outputFileName);
                dataUtils.clearFile(chatDataUtils.outputFileName);
                dataUtils.clearFile(dataUtils.activityFileName);
                
            }   
            
            // In Minecraft time (ticks), one human second is 20 ticks, so one human minute is 20*60 = 1200 ticks.
            // Multiply 1200 by the desired amount of minutes to repeat this task
        }.runTaskTimer(this, 20*60*period, 20*60*period);
        
        // Broadcast Message Loop
        new BukkitRunnable() 
        {
            public void run()
            {
                Bukkit.broadcast(componentUtils.activityMessage);
            }
        }.runTaskTimer(this, 20 * cd, 20 * 30);
    }

    @Override
    public void onDisable()
    {
        mongoClient.close();
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled.");
    }

    // To-Do list
    // 1. Ensure data goes to their respective collections
    // 2. Make sure the hash works with multiple players
    // 3. Associate a player with a number via a HashMap? (Post-data collection)
}
