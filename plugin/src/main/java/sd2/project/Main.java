package sd2.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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

    // The period of time to send data in minutes.
    static final int period = 1;

    @Override
    public void onEnable()
    {
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);

        this.getCommand("sendData").setExecutor(new SendData());
        this.getCommand("clearData").setExecutor(new ClearData());   
        
        // Send data loop
        new BukkitRunnable()
        {
            public void run()
            {
               
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB() + " documents this time around.");
                dataUtils.clearFile(dataUtils.outputFileName);

            }   

        // In Minecraft time (ticks), one human second is 20 ticks, so one human minute is 20*60 = 1200 ticks.
        // Multiply 1200 by the desired amount of minutes to repeat this task
        }.runTaskTimer(this, 20*60*period, 20*60*period);
        
    }

    @Override
    public void onDisable()
    {
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled.");
    }

    // To-Do list
    // 1. Reformat data
    // 2. Set timer for sending data and clearing file
    // 3. Add the remainder of the events
}
