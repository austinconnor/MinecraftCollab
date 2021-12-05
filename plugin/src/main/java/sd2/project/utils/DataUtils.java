package sd2.project.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import sd2.project.Main;

public class DataUtils 
{
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private FileWriter outputFile;
    public String outputFileName = "output.json";
    public String activityFileName = "activity.json";
	public String dataURI = "placeholder";

    public String hashPlayer(Player p)
    {
        return DigestUtils.md5Hex(p.getName()); 
    }

    public String hashPlayer(String playerName)
    {
        return DigestUtils.md5Hex(playerName);
    }

    // Use HeapCraft tab action data and add it as a parameter for the data

    // This method provides us a useable JSON object that we can tweak to our needs.
    // We take a player as a parameter since we would like to get their location.
    public JsonObject packageData(Player player, Event e)
    {
        JsonObject json = new JsonObject();
        JsonObject locationData = new JsonObject();

        // This locationData object will be used to store the player's location
        locationData.addProperty("x", player.getLocation().getBlockX());
        locationData.addProperty("y", player.getLocation().getBlockY());
        locationData.addProperty("z", player.getLocation().getBlockZ());

        // Adding necessary data to our object.
        json.add("location", gson.toJsonTree(locationData));
        json.addProperty("player", hashPlayer(player));
        json.addProperty("worldName", player.getWorld().getName());
        json.addProperty("worldTime", player.getWorld().getFullTime());
        json.addProperty("event", e.getEventName());
        

        // An example of an output: { "location": { "x": 233,"y": 72,"z": 92}, "worldName": "world","worldTime": 4764,"playerID": "69420","event": "PlayerMoveEvent"}

        return json;
    }

    public JsonObject packageActionData(Player player, String activity, TreeMap<Double, ItemStack> map)
    {
        JsonObject json = new JsonObject();
        JsonObject locationData = new JsonObject();
        JsonObject nearbyPlayers = new JsonObject();

        // This locationData object will be used to store the player's location
        locationData.addProperty("x", player.getLocation().getBlockX());
        locationData.addProperty("y", player.getLocation().getBlockY());
        locationData.addProperty("z", player.getLocation().getBlockZ());

        // Adding necessary data to our object.
        json.add("location", gson.toJsonTree(locationData));
        json.addProperty("player", hashPlayer(player));
        json.addProperty("worldName", player.getWorld().getName());
        json.addProperty("worldTime", player.getWorld().getFullTime());

        json.addProperty("activity", activity);

        Integer count = 0;

        // Add nearby players.

        JsonObject playerObj;
        double num;

        for (Map.Entry<Double,ItemStack> entry : map.entrySet())
        {
            playerObj = new JsonObject();
            num = Math.round(entry.getKey() * 100);
            num = num / 100;

            String nearbyPlayer = entry.getValue().getItemMeta().getDisplayName().substring(4);

            
            playerObj.addProperty("player", hashPlayer(nearbyPlayer));
            playerObj.addProperty("distance", num);
            
            nearbyPlayers.add(Integer.toString(count), gson.toJsonTree(playerObj));

            count++;
        }

        json.add("nearbyPlayers", gson.toJsonTree(nearbyPlayers));
        


        return json;
    }

    // Upon successfully writing to the local file, return true.
    public boolean writeToFile(JsonObject data, String fileName)
    {
        try
        {
            // Declare and write to the output file.
            outputFile = new FileWriter(fileName, true);
            outputFile.append(data.toString() + "\n");
            outputFile.close();
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    // Converts output.json into an ArrayList
    public List<Document> parseFileToList(String fileName)
    {
        ArrayList<Document> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                Document document = Document.parse(line);
                data.add(document);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return data;
    }

    public boolean clearFile(String fileName)
    {
        try
        {
            PrintWriter writer = new PrintWriter(fileName);
            writer.print("");
            writer.close();
        }
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}

        return true;
    }

    public boolean clearAllFiles()
    {
        int count = 0;
        for (String s : Main.fileNames)
        {
            clearFile(s);
            count++;
        }

        if (count == Main.fileNames.size())
            return true;

        return false;
    }

    // Uploads current data to the database.
    public int writeToDB(String dbName, String outputFile)
    {
        
        MongoCollection<Document> collection = Main.database.getCollection(dbName);

        List<Document> dataList = parseFileToList(outputFile);

        if (dataList.isEmpty())
            return 0;

        // Insert data
        try 
        {
            collection.insertMany(dataList);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return dataList.size();
    }

    public boolean sendAllToDB()
    {
        int i = 0;
        for (Map.Entry<String, String> entry : Main.collections.entrySet())
        {
            Bukkit.getLogger().info(Main.prefix + ChatColor.GREEN + "Sent " + writeToDB(entry.getKey(), Main.fileNames.get(i)) + " " + entry.getValue() + " data documents this time around.");
            i++;
        }

        if (i == Main.fileNames.size())
            return true;

        return false;
    }
}
