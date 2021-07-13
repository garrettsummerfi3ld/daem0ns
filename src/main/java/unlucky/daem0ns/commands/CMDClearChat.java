package unlucky.daem0ns.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import unlucky.daem0ns.utils.Chat;

public class CMDClearChat implements CommandExecutor {
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
        if (!(sender instanceof Player)) {
            sender.sendMessage("NOT VERIFIED");
        }

        Player p = (Player) sender;

        if (sender.hasPermission("daem0ns.clearchat")) {
            for (int i = 0; i < 71; i++) {
                for (Player player : Bukkit.getOnlinePlayers()){
                    player.sendMessage("\n");
                }
            }
            Bukkit.broadcastMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &7&oThe chat has been cleared"));
        } else {
            p.sendMessage("&8[&7daem&80&7ns&8] &cYou do not have permission to use this command");
        }
        return true;
    }
}
