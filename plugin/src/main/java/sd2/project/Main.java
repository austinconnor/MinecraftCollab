package sd2.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import sd2.project.cmds.ClearData;
import sd2.project.cmds.SendData;
import sd2.project.events.Events;
import sd2.project.utils.DataUtils;

public class Main extends JavaPlugin
{
    // Chat prefix [DC] <message>
    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + 
                                        ChatColor.BOLD + "D" + ChatColor.WHITE + ChatColor.BOLD + "C" + 
                                        ChatColor.RESET + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    public DataUtils dataUtils = new DataUtils();

    @Override
    public void onEnable()
    {
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);

        this.getCommand("sendData").setExecutor(new SendData());
        this.getCommand("clearData").setExecutor(new ClearData());        
    }

    @Override
    public void onDisable()
    {
        // Send data & clear on disable
        int numRecords = dataUtils.writeToDB();
        dataUtils.clearFile(dataUtils.outputFileName);

        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled and uploaded " + numRecords + "documents before shutting down.");
    }

    // To-Do list
    // 1. Reformat data
    // 2. Set timer for sending data and clearing file
    // 3. Add the remainder of the events
}
