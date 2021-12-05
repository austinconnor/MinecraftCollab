package sd2.project.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import sd2.project.Main;
import sd2.project.utils.InventoryUtils;

public class Activity implements CommandExecutor 
{

    public Main plugin;

    public Activity(Main instance)
    {
        plugin = instance;
    }

    InventoryUtils invUtils = new InventoryUtils();

    int cd = 270;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String [] args)
    {
        if (label.equalsIgnoreCase("act") || label.equalsIgnoreCase("activity"))
        {
            if (sender instanceof Player)
            {
                Player p = (Player) sender;

                if (!Main.cooldown.contains(p.getUniqueId(), "act"))
                {
                    if (p.getGameMode() == GameMode.SURVIVAL)
                    {
                        // Open inventory
                        p.openInventory(Main.actInv);
                    }
                    else
                    {
                        p.sendMessage(Main.prefix + ChatColor.RED + "You must be in SURVIVAL mode to execute this command.");
                    }
                }
                else
                {
                    int timeRemaining = Main.cooldown.get(p.getUniqueId(), "act");
                    p.sendMessage(Component.text(Main.prefix + ChatColor.GRAY + "You cannot use this command for another " + 
                                                ChatColor.RED + timeRemaining + ChatColor.GRAY + " second" + ((timeRemaining == 1) ? "." : "s.")));
                }
            }
            else
            {
                sender.sendMessage(Main.prefix + "You must be a player to execute this command.");
            }
        }

        return true;
    }
}
