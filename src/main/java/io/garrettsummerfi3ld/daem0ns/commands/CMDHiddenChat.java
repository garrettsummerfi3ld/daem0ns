package io.garrettsummerfi3ld.daem0ns.commands;

import io.garrettsummerfi3ld.daem0ns.utils.Chat;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDHiddenChat implements CommandExecutor {

    /**
     * Checks if arguments are more than zero
     *
     * @param args Message to send to chat
     * @return true if args are more than 0, otherwise false
     */
    public boolean checkArgs(String @NotNull [] args) {
        return args.length > 0;
    }

    /**
     * Sends a message to the server without a player associated
     *
     * @param args Message to send to chat
     */
    public void sendHiddenMessage(String @NotNull [] args){
        StringBuilder s = new StringBuilder();
        for (String arg : args) s.append(arg).append(" ");
        Bukkit.broadcastMessage(s.toString());
    }

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Hidden chat command
     * @param label   Alias of the command which was used
     * @param args    Chat message from user
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (sender.hasPermission("daem0ns.hide")) {
                if (checkArgs(args)) {
                    sendHiddenMessage(args);
                }
                else {
                    p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou need to have text to submit"));
                }
            } else {
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou are not authorized to use this command"));
            }
        }
        else {
            if (checkArgs(args)) {
                sendHiddenMessage(args);
            }
            else {
                sender.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou need to have text to submit"));
            }
        }
        return true;
    }
}
