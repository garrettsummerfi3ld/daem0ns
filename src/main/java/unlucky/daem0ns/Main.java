package unlucky.daem0ns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import unlucky.daem0ns.commands.CMDClearChat;
import unlucky.daem0ns.commands.CMDHiddenChat;
import unlucky.daem0ns.commands.CMDMuteAll;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "daem0ns is starting...");
        loadCommands();
        loadListeners();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "daem0ns has been enabled.");
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("hide")).setExecutor(new CMDHiddenChat());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new CMDClearChat());
        Objects.requireNonNull(getCommand("muteall")).setExecutor(new CMDMuteAll());
    }

    private void loadListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new CMDMuteAll(), this);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "daem0ns has been disabled.");
    }
}
