package io.garrettsummerfi3ld.daem0ns.commands;

import io.garrettsummerfi3ld.daem0ns.utils.Chat;
import io.garrettsummerfi3ld.daem0ns.utils.OpTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CMDOpTool implements CommandExecutor {

    /**
     * Command processing for the OpTool
     * @param sender CommandSender Person who ran the command, can be a player or the console
     * @param args String[] arguments for the command, can be player and what you want to do with the command
     */
    public void opCmd(CommandSender sender, String[] args) {
        Player user = Bukkit.getPlayer(args[1]);
        switch (args[0]) {
            case "add":
                OpTool.addOpUser(Objects.requireNonNull(user));
                break;
            case "delete":
                OpTool.delOpUser(Objects.requireNonNull(user));
                break;
            case "status":
            default:
                if (OpTool.checkOpUser(Objects.requireNonNull(user)))
                    sender.sendMessage(Chat.colorMsg("8[&7daem&80&7ns&8] &cUser " + user.getName() + "is &cOPPED"));
                else
                    sender.sendMessage(Chat.colorMsg("8[&7daem&80&7ns&8] &cUser " + user.getName() + "is &2NOT OPPED"));
        }
    }

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
            if (sender.hasPermission("daem0ns.optool")) {
                opCmd(sender, args);
            } else {
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou are not authorized to use this command"));
            }
        } else {
            opCmd(sender, args);
        }
        return true;
    }
}
