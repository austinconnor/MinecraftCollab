package sd2.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import sd2.project.cmds.ClearData;
import sd2.project.cmds.SendData;
import sd2.project.events.Events;

/**
 * Hello world!
 *
 */

public class Main extends JavaPlugin
{

    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + 
                                        ChatColor.BOLD + "D" + ChatColor.WHITE + ChatColor.BOLD + "C" + 
                                        ChatColor.RESET + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;

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
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled.");
    }
}
