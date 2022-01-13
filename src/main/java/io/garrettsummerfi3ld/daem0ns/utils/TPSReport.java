package io.garrettsummerfi3ld.daem0ns.utils;

import io.garrettsummerfi3ld.daem0ns.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.garrettsummerfi3ld.daem0ns.utils.Chat.colorMsg;


public class TPSReport extends BukkitRunnable {
    private final JavaPlugin plugin;
    private final Logger logger = Main.log;
    private boolean unstableTPSCheck = false;

    public TPSReport(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Checks TPS from the config file set up, if the TPS pulled from the API returns lower than the set config, then
     * it will alert staff with the permission <code>daem0ns.perfreport</code> to the lag notice, and will run <code>tps</code>
     * and <code>timings report</code> if there is an issue
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        double[] tps = Bukkit.getTPS();
        double[] tpsConfig = { Main.getInstance().getConfig().getDouble("tps.1m"),
                Main.getInstance().getConfig().getDouble("tps.15m"),
                Main.getInstance().getConfig().getDouble("tps.5m"),
        };
        if (tps[0] <= tpsConfig[0] || tps[1] <= tpsConfig[1] || tps[2] <= tpsConfig[2]) {
            if (!unstableTPSCheck) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("daem0ns.perfreport")) {
                        p.sendMessage(colorMsg("&8[&7daem&80&7ns&8] &cThe TPS is critically low, check the console for more information."));
                    }
                }
                logger.warning(colorMsg("&8[&7daem&80&7ns&8] &cServer is lagging, running TPS report..."));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tps");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timings report");
                unstableTPSCheck = true;
            }
        } else {
            if (Main.getInstance().getConfig().getBoolean("tps.console-messages"))
                Bukkit.getLogger().log(Level.INFO, colorMsg("&8[&7daem&80&7ns&8] Server is running normally."));
            unstableTPSCheck = false;
        }
    }
}
