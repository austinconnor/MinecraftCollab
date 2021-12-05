package sd2.project.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.kyori.adventure.text.Component;
import sd2.project.Main;

public class InventoryUtils 
{
    public void fillGlass(Inventory inv)
    {
        for (int i = 0; i < inv.getSize(); i++)
        {
            if (inv.getItem(i) == new ItemStack(Material.AIR) || inv.getItem(i) == null)
            {
                inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));                            
            }
        }
    }

    public Inventory createActivityInventory()
    {
        Inventory inv = Bukkit.createInventory(null, 27, Component.text(Main.prefix + ChatColor.DARK_PURPLE + "What are you doing?"));

        ItemStack mine = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta minem = mine.getItemMeta();
        minem.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + "Mining"));
        ArrayList<Component> minel = new ArrayList<>();
        minel.add(Component.text("The Mining action. This means you"));
        minel.add(Component.text("are most likely in some cave finding"));
        minel.add(Component.text("resources of some sort."));
        minem.lore(minel);
        mine.setItemMeta(minem);

        ItemStack explore = new ItemStack(Material.MAP);
        ItemMeta explorem = explore.getItemMeta();
        explorem.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + "Exploring"));
        ArrayList<Component> explorel = new ArrayList<>();
        explorel.add(Component.text("The Exploring action. This means you"));
        explorel.add(Component.text("are out finding new areas and what"));
        explorel.add(Component.text("they have to offer."));
        explorem.lore(explorel);
        explore.setItemMeta(explorem);

        ItemStack building = new ItemStack(Material.BRICKS);
        ItemMeta buildingm = building.getItemMeta();
        buildingm.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + "Building"));
        ArrayList<Component> buildingl = new ArrayList<>();
        buildingl.add(Component.text("The Building action. This means you"));
        buildingl.add(Component.text("are creating some strucutre in-game"));
        buildingl.add(Component.text("whether it is a house, xp grinder, or"));
        buildingl.add(Component.text("any other structure."));
        buildingm.lore(buildingl);
        building.setItemMeta(buildingm);

        ItemStack idle = new ItemStack(Material.CAKE);
        ItemMeta idlem = idle.getItemMeta();
        idlem.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + "Idle"));
        ArrayList<Component> idlel = new ArrayList<>();
        idlel.add(Component.text("The Idle action. This means you"));
        idlel.add(Component.text("are most likely hanging out with a"));
        idlel.add(Component.text("group of people or might not have"));
        idlel.add(Component.text("something specific in mind to do."));
        idlem.lore(idlel);
        idle.setItemMeta(idlem);

        inv.setItem(10, mine);
        inv.setItem(12, explore);
        inv.setItem(14, building);
        inv.setItem(16, idle);

        fillGlass(inv);

        return inv;
    }

    public Inventory createActivityInventory(Player p, Component action)
    {
        // Create an inventory with 6 rows of slots (columns must be 9 in size)
        Inventory inv = Bukkit.createInventory(p, 9 * 6, action);

        ItemStack eye = new ItemStack(Material.ENDER_EYE);
        ItemMeta eyem =  eye.getItemMeta();
        eyem.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + "Who's nearby..?"));
        ArrayList<Component> eyel = new ArrayList<>();
        eyel.add(Component.text("Below should show neabry players"));
        eyel.add(Component.text("that are around you."));
        eyem.lore(eyel);
        eye.setItemMeta(eyem);

        ItemStack cancel = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta cancelm = cancel.getItemMeta();
        cancelm.displayName(Component.text(ChatColor.RED + "" + ChatColor.BOLD + "CANCEL"));
        ArrayList<Component> cancell = new ArrayList<>();
        cancell.add(Component.text("Cancel selection and go back."));
        cancelm.lore(cancell);
        cancel.setItemMeta(cancelm);

        ItemStack submit = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta submitm = submit.getItemMeta();
        submitm.displayName(Component.text(ChatColor.GREEN + "" + ChatColor.BOLD + "SUBMIT"));
        ArrayList<Component> submitl = new ArrayList<>();
        submitl.add(Component.text("Submit selection and exit."));
        submitm.lore(submitl);
        submit.setItemMeta(submitm);

        inv.setItem(4, eye);
        inv.setItem(48, cancel);
        inv.setItem(50, submit);

        fillGlass(inv);
        
        return inv;
    }

    public ItemStack getPlayerHead(Player p, double distance)
    {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
        skullMeta.setOwningPlayer(p);
        ArrayList<Component> lore = new ArrayList<>();

        double num = Math.round(Math.sqrt(distance) * 100);
        num = num / 100;

        lore.add(Component.text(ChatColor.GOLD + "Distance: " + num + "m"));
        skullMeta.lore(lore);
        skullMeta.displayName(Component.text(ChatColor.GOLD + "" + ChatColor.BOLD + p.getName()));
        playerHead.setItemMeta(skullMeta);

        return playerHead;
    }
}
