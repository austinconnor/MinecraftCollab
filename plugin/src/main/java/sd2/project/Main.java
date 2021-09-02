package sd2.project;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

import sd2.project.events.Events;

/**
 * Hello world!
 *
 */

public class Main extends JavaPlugin
{

    public static final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + ChatColor.BOLD + "D" + ChatColor.WHITE + ChatColor.BOLD + "C" + ChatColor.RESET + ChatColor.DARK_GRAY + "] ";

    @Override
    public void onEnable()
    {
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been enabled.");
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable()
    {
        getLogger().info(prefix + ChatColor.RED + "DataCollection" + ChatColor.GRAY + " been disabled.");
    }
}
