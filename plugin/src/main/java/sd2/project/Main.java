package sd2.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import sd2.project.cmds.ClearData;
import sd2.project.cmds.SendData;
import sd2.project.events.Events;
import sd2.project.utils.ChatDataUtils;
import sd2.project.utils.DataUtils;

public class Main extends JavaPlugin
{
    // Chat prefix [DC] <message>
    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + 
                                        ChatColor.BOLD + "D" + ChatColor.WHITE + ChatColor.BOLD + "C" + 
                                        ChatColor.RESET + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

    public DataUtils dataUtils = new DataUtils();
    public ChatDataUtils chatDataUtils = new ChatDataUtils();

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
               
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB("worldData", dataUtils.outputFileName) + " PLAYER data documents this time around.");
                getLogger().info(prefix + ChatColor.GREEN + "Sent " + dataUtils.writeToDB("chatData", chatDataUtils.outputFileName) + " CHAT data documents this time around.");
                dataUtils.clearFile(dataUtils.outputFileName);
                dataUtils.clearFile(chatDataUtils.outputFileName);

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
    // 1. Ensure data goes to their respective collections
    // 2. Make sure the hash works with multiple players
    // 3. Associate a player with a number via a HashMap? (Post-data collection)
}
