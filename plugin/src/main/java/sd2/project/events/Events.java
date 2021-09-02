package sd2.project.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.Location;

import sd2.project.Main;

public class Events implements Listener
{
    @EventHandler
    public void onBadMoveEvent(PlayerMoveEvent e)
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

        if(fromX != toX || fromY != toY || fromZ != toZ){
            //collect location data in this if statement
            //Player Location information:
            //fromLoc.getBlockX()
            //fromLoc.getBlockY()
            //fromLoc.getBlockZ()

            Bukkit.broadcastMessage(Main.prefix + "Event fired.");
        }
    }
}
