package sd2.project.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.md_5.bungee.api.ChatColor;

public class ComponentUtils
{
    public final TextComponent activityMessage = Component.text(ChatColor.GREEN + "Share what you are doing with us! Use " + ChatColor.AQUA + "/act " + ChatColor.GREEN + "or click this message!")
                                .hoverEvent(Component.text("Click me to execute " + ChatColor.AQUA + "/act"))
                                .clickEvent(ClickEvent.runCommand("/act"));        
}
