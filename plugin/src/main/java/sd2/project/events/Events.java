package sd2.project.events;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonObject;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;

import sd2.project.Main;
import sd2.project.utils.DataUtils;

public class Events implements Listener
{
    DataUtils dataUtils = new DataUtils();
    private FileWriter outputFile;
    
    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        Location fromLoc = e.getFrom();
        Location toLoc = e.getTo();
        int fromX, fromY, fromZ;
        int toX, toY, toZ;

        fromX = fromLoc.getBlockX();
        fromY = fromLoc.getBlockY();
        fromZ = fromLoc.getBlockZ();

        toX = toLoc.getBlockX();
        toY = toLoc.getBlockY();
        toZ = toLoc.getBlockZ();

        if(fromX != toX || fromY != toY || fromZ != toZ)
        {
            JsonObject data = dataUtils.packageData(player);

            try
            {
                outputFile = new FileWriter("output.json", true);
                outputFile.append(data.toString());
                player.sendMessage("Sent.");

                outputFile.close();

            }
            catch(IOException exception)
            {
                exception.printStackTrace();
            }

            Bukkit.broadcastMessage(Main.prefix + "JSON: " + data.toString());
        }
    }
}
