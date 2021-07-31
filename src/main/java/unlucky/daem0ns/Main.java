package unlucky.daem0ns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import unlucky.daem0ns.commands.CMDClearChat;
import unlucky.daem0ns.commands.CMDHiddenChat;
import unlucky.daem0ns.commands.CMDServInfo;
import unlucky.daem0ns.commands.CMDMuteAll;
import unlucky.daem0ns.utils.TPSReport;

import java.util.Objects;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();
    private static Main instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.INFO,ChatColor.RED + "daem0ns is starting...");

        this.saveDefaultConfig();

        loadCommands();
        loadListeners();
        loadSchedulers();

        Bukkit.getLogger().log(Level.INFO,ChatColor.RED + "daem0ns has been enabled.");
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("hide")).setExecutor(new CMDHiddenChat());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new CMDClearChat());
        Objects.requireNonNull(getCommand("muteall")).setExecutor(new CMDMuteAll());
        //Objects.requireNonNull(getCommand("srvinfo")).setExecutor(new CMDServInfo());
    }

    private void loadListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new CMDMuteAll(), this);
    }

    private void loadSchedulers() {
        BukkitTask TPSReporter = new TPSReport(this).runTaskTimer(this, 20, 900);
    }

    // Sets instance
    public Main() {
        if(Main.instance != null) {
            throw new Error("daem0ns is already initialized!");
        }
        Main.instance = this;
    }

    // Gets instance
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO,ChatColor.RED + "daem0ns has been disabled.");
    }
}
