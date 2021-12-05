package sd2.project;

import java.util.ArrayList;
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
    // Activity message delay
    int cdActivity = 300;

    // Database objects
    public static MongoClient mongoClient = null;
    public static MongoDatabase database = null;

    // Utils for later use
    public DataUtils dataUtils = new DataUtils();
    public ChatDataUtils chatDataUtils = new ChatDataUtils();
    public ComponentUtils componentUtils = new ComponentUtils();
    public InventoryUtils invUtils = new InventoryUtils();

    // Table for tracking all cooldowns
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

    // Tracks nearby players for the activity feature
    public static HashMap<Player, TreeMap<Double, ItemStack>> proximityData = new HashMap<>();

    // File Names
    public static ArrayList<String> fileNames = new ArrayList<>(); 

    // Collection Map
    public static HashMap<String, String> collections = new HashMap<>();

    @Override
    public void onEnable()
    {
        // Establish connection to DB
        mongoClient = new MongoClient(new MongoClientURI(dataUtils.dataURI));
        database = mongoClient.getDatabase("Data");

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new ActivityEvents(this), this);
        pm.registerEvents(new Events(), this);
        
        // Place all commands here along with their respective classes.
        this.getCommand("sendData").setExecutor(new SendData());
        this.getCommand("clearData").setExecutor(new ClearData());
        this.getCommand("act").setExecutor(new Activity(this));   
        this.getCommand("activity").setExecutor(new Activity(this));  
        
        actInv = invUtils.createActivityInventory();
        
        getLogger().info(prefix + ChatColor.RED + "Inventories " + ChatColor.GRAY + "have been loaded.");

        fileNames.add(dataUtils.outputFileName);
        fileNames.add(chatDataUtils.outputFileName);
        fileNames.add(dataUtils.activityFileName);

        getLogger().info(prefix + ChatColor.RED + "File Names" + ChatColor.GRAY + "have been loaded");

        // Parameter format: <collection, dataType>
        collections.put("worldData", "PLAYER");
        collections.put("chatData", "CHAT");
        collections.put("activityData", "ACTIVITY");

        getLogger().info(prefix + ChatColor.RED + "DataCollection " + ChatColor.GRAY + "been enabled.");
        

        // Send data loop
        new BukkitRunnable()
        {
            public void run()
            {
                dataUtils.sendAllToDB();
                dataUtils.clearAllFiles();                
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
        }.runTaskTimer(this, 20 * 30, 20 * cdActivity);
    }

    @Override
    public void onDisable()
    {
        mongoClient.close();
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled.");
    }

}
