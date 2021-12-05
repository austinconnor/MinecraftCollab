package sd2.project.utils;

import com.google.gson.JsonObject;

import org.bukkit.entity.Player;

import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.text.PaperComponents;

public class ChatDataUtils 
{
    DataUtils dataUtils = new DataUtils();

    public String outputFileName = "chatOutput.json";
    public String chatURI = "placeholder";

    public JsonObject packageChatData(Player player, AsyncChatEvent e)
    {
        JsonObject json = new JsonObject();

        // Adding necessary data to our object.

        String message = PaperComponents.plainSerializer().serialize(e.originalMessage());

        json.addProperty("player", dataUtils.hashPlayer(player));
        json.addProperty("time", player.getWorld().getFullTime());
        json.addProperty("message", message);
        
        return json;
    }
}
