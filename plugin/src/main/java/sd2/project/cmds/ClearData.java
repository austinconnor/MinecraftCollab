package sd2.project.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sd2.project.Main;
import sd2.project.utils.DataUtils;

public class ClearData implements CommandExecutor
{
    DataUtils dataUtils = new DataUtils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("clearData"))
        {
            if (sender instanceof Player)
            {
                Player p = (Player) sender;

                if (p.hasPermission("data.admin"))
                {
                    dataUtils.clearFile(dataUtils.outputFileName);
                    p.sendMessage(Main.prefix + ChatColor.GREEN + "Cleared output.json!");
                    return true;
                }
                else
                {
                    p.sendMessage(Main.prefix + ChatColor.RED + "You do not have permission to execute this command.");
                    return false;
                }
            }
            dataUtils.clearFile(dataUtils.outputFileName);
            dataUtils.clearFile(dataUtils.activityFileName);
            System.out.println(Main.prefix + ChatColor.GREEN + "Cleared output file!");
        }
        
        return true;
    }
}
