package unlucky.daem0ns.commands;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import unlucky.daem0ns.utils.Chat;

public class CMDMuteAll implements CommandExecutor, Listener {
    public static boolean serverMuted = false;

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (sender.hasPermission("daem0ns.muteall")) {
                serverMuted = !serverMuted;
                Bukkit.broadcastMessage(Chat.colorMsg(serverMuted ? ("&8[&7daem&80&7ns&8] &7&oThe server has been muted by &c" + p.getPlayer().getName()) : "&8[&7daem&80&7ns&8] &7&oThe server has been unmuted"));
            } else {
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou are not authorized to use this command"));
            }
        }
        else {
            serverMuted = !serverMuted;
            Bukkit.broadcastMessage(Chat.colorMsg(serverMuted ? ("&8[&7daem&80&7ns&8] &7&oThe server has been muted by &cCONSOLE") : "&8[&7daem&80&7ns&8] &7&oThe server has been unmuted"));
        }
        return true;
    }

    public static boolean getServerMuteState(){
        return serverMuted;
    }
}
