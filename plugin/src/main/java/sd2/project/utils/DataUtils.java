package sd2.project.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class DataUtils 
{
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private FileWriter outputFile;
    public String outputFileName = "output.json";
	public String dataURI = "placeholder";


    public String hashPlayer(Player p)
    {
        return DigestUtils.md5Hex(p.getName()); 
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

        Bukkit.broadcastMessage(json.toString());

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

    // Uploads current data to the database.
    public int writeToDB(String uri, String outputFile)
    {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));
        MongoDatabase database = mongoClient.getDatabase("Data");
        MongoCollection<Document> collection = database.getCollection("worldData");

        List<Document> dataList = parseFileToList(outputFile);

        // Get the file output.json
        try 
        {
            System.out.println("Size of list" + dataList.size());
            collection.insertMany(dataList);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        mongoClient.close();
        return dataList.size();
    }
}
