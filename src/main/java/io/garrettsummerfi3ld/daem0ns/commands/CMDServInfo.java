package io.garrettsummerfi3ld.daem0ns.commands;

import io.garrettsummerfi3ld.daem0ns.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CMDServInfo implements CommandExecutor {

    private final Server server = Bukkit.getServer();

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
        if (sender instanceof Player p){
            if (p.hasPermission("daem0ns.serverinfo")) {
                String name = server.getName();
                String version = server.getVersion();
                String bukkitVersion = server.getBukkitVersion();
                String plugins = Arrays.toString(server.getPluginManager().getPlugins());
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &7Server Information"));
                p.sendMessage(Chat.colorMsg("Name: ").concat(name));
                p.sendMessage(Chat.colorMsg("Version: ").concat(version));
                p.sendMessage(Chat.colorMsg("Bukkit Version: ").concat(bukkitVersion));
                p.sendMessage(Chat.colorMsg("Plugins: ").concat(plugins));
            }
            else{
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou are not authorized to use this command"));
            }
        }
        return true;
    }
}
