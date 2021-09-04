package sd2.project.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.bukkit.entity.Player;

public class DataUtils 
{
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public int hashCode(Player player)
    {
        return player.getName().hashCode() * player.getUniqueId().hashCode();
    }

    // This method provides us a useable JSON object that we can tweak to our needs.
    // We take a player as a parameter since we would like to get their location.
    public JsonObject packageData(Player player)
    {

        JsonObject json = new JsonObject();
        JsonObject locationData = new JsonObject();

        locationData.addProperty("worldName", player.getWorld().getName());
        locationData.addProperty("x", player.getLocation().getBlockX());
        locationData.addProperty("y", player.getLocation().getBlockY());
        locationData.addProperty("z", player.getLocation().getBlockZ());
        locationData.addProperty("worldTime", player.getWorld().getTime());

        json.addProperty("player", player.getName());
        json.add("location", gson.toJsonTree(locationData));
    

        return json;
    }

}
