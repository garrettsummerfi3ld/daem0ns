package unlucky.daem0ns.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unlucky.daem0ns.utils.Chat;

public class CMDHiddenChat implements CommandExecutor {
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
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("daem0ns.hide")) {
                if (args.length == 0) {
                    p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou need to have text to submit"));
                }
                if (args.length > 0) {
                    StringBuilder s = new StringBuilder();
                    for (String arg : args) s.append(arg).append(" ");
                    Bukkit.broadcastMessage(Chat.colorMsg(s.toString()));
                }
            } else {
                p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cYou are not authorized to use this command"));
            }
        } else {
            sender.sendMessage("[daem0ns] Only in-game players are allowed to use this command");
        }

        return true;
    }
}
