package unlucky.daem0ns;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import unlucky.daem0ns.commands.CMDClearChat;
import unlucky.daem0ns.commands.CMDHiddenChat;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "daem0ns has been enabled.");
        Objects.requireNonNull(getCommand("hide")).setExecutor(new CMDHiddenChat());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new CMDClearChat());
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "daem0ns has been disabled.");
    }
}
