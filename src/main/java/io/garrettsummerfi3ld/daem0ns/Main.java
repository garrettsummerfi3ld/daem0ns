package io.garrettsummerfi3ld.daem0ns;

import io.garrettsummerfi3ld.daem0ns.commands.CMDClearChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import io.garrettsummerfi3ld.daem0ns.commands.CMDHiddenChat;
import io.garrettsummerfi3ld.daem0ns.commands.CMDMuteAll;
import io.garrettsummerfi3ld.daem0ns.listeners.MuteAllListener;
import io.garrettsummerfi3ld.daem0ns.utils.TPSReport;

import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();
    private static Main instance;

    public static final Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        logger.info(ChatColor.RED + "daem0ns is starting...");

        this.saveDefaultConfig();

        loadCommands();
        loadListeners();
        loadSchedulers();

        logger.info(ChatColor.RED + "daem0ns has been enabled.");
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("hide")).setExecutor(new CMDHiddenChat());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new CMDClearChat());
        Objects.requireNonNull(getCommand("muteall")).setExecutor(new CMDMuteAll());
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new MuteAllListener(), this);
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
        logger.info(ChatColor.RED + "daem0ns has been disabled.");
    }
}
