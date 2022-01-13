package io.garrettsummerfi3ld.daem0ns;

import io.garrettsummerfi3ld.daem0ns.commands.CMDClearChat;
import io.garrettsummerfi3ld.daem0ns.commands.CMDHiddenChat;
import io.garrettsummerfi3ld.daem0ns.commands.CMDMuteAll;
import io.garrettsummerfi3ld.daem0ns.listeners.MuteAllListener;
import io.garrettsummerfi3ld.daem0ns.utils.TPSReport;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static final Logger log = Bukkit.getLogger();
    private static Main instance;
    FileConfiguration config = this.getConfig();

    // Sets instance
    public Main() {
        if (Main.instance != null) {
            throw new Error("daem0ns is already initialized!");
        }
        Main.instance = this;
    }

    // Gets instance
    public static Main getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        log.info("daem0ns is starting...");

        this.saveDefaultConfig();
        config.options().copyDefaults(true);
        saveConfig();

        loadCommands();
        loadListeners();
        loadSchedulers();

        log.info("daem0ns has been enabled.");
    }

    private void loadCommands() {
        Objects.requireNonNull(getCommand("hide")).setExecutor(new CMDHiddenChat());
        Objects.requireNonNull(getCommand("clearchat")).setExecutor(new CMDClearChat());
        Objects.requireNonNull(getCommand("muteall")).setExecutor(new CMDMuteAll());
    }

    private void loadListeners() {
        getServer().getPluginManager().registerEvents(new MuteAllListener(), this);
    }

    /**
     * Loads schedulers and tasks, such as the TPS checker
     */
    private void loadSchedulers() {
        new TPSReport(this).runTaskTimer(this, 20, 900);
    }

    @Override
    public void onDisable() {
        log.info("daem0ns has been disabled.");
    }
}
