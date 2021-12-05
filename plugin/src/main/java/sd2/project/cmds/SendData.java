package sd2.project.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import sd2.project.Main;
import sd2.project.utils.ChatDataUtils;
import sd2.project.utils.DataUtils;

public class SendData implements CommandExecutor
{

    DataUtils dataUtils = new DataUtils();
    ChatDataUtils chatDataUtils = new ChatDataUtils();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("sendData"))
        {
            if (sender instanceof Player)
            {
                Player p = (Player) sender;

                if (p.hasPermission("data.admin"))
                {
                    p.sendMessage(Main.prefix + ChatColor.GREEN + "Uploaded " + dataUtils.writeToDB("worldData", dataUtils.outputFileName) + " data documents.");
                    p.sendMessage(Main.prefix + ChatColor.GREEN + "Uploaded " + dataUtils.writeToDB("chatData", chatDataUtils.outputFileName) + " chat documents.");
                    return true;
                }
                else
                {
                    p.sendMessage(Main.prefix + ChatColor.RED + "You do not have permission to execute this command.");
                    return false;
                }
            } 
            System.out.println(Main.prefix + ChatColor.GREEN + "Uploaded " + dataUtils.writeToDB("worldData", dataUtils.outputFileName) + " data documents.");
            System.out.println(Main.prefix + ChatColor.GREEN + "Uploaded " + dataUtils.writeToDB("chatData", chatDataUtils.outputFileName) + " chat documents.");

            dataUtils.clearFile(dataUtils.outputFileName);
            dataUtils.clearFile(chatDataUtils.outputFileName);
        }
        
        return true;
    }
}
