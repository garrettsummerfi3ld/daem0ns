package io.garrettsummerfi3ld.daem0ns.utils;

import io.garrettsummerfi3ld.daem0ns.Main;
import me.lucko.spark.api.statistic.StatisticWindow;
import me.lucko.spark.api.statistic.types.DoubleStatistic;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Logger;

import static io.garrettsummerfi3ld.daem0ns.utils.Chat.colorMsg;

public class TPSReport extends BukkitRunnable {
    private static final Main main = Main.getInstance();
    private static final Logger log = Main.log;
    private boolean unstableTPSCheck = false;
    DoubleStatistic<StatisticWindow.TicksPerSecond> tps = Main.getSparkProvider().tps();

    public TPSReport(JavaPlugin plugin) {
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
        double[] tpsLive = {
                tps.poll(StatisticWindow.TicksPerSecond.SECONDS_5),
                tps.poll(StatisticWindow.TicksPerSecond.MINUTES_1),
                tps.poll(StatisticWindow.TicksPerSecond.MINUTES_5),
                tps.poll(StatisticWindow.TicksPerSecond.MINUTES_15)
        };
        double[] tpsConfig = {
                main.getConfig().getDouble("tps.5s"),
                main.getConfig().getDouble("tps.1m"),
                main.getConfig().getDouble("tps.5m"),
                main.getConfig().getDouble("tps.15m"),
        };
        for (int i = 0; i < tpsLive.length; i++) {
            if (tpsLive[i] <= tpsConfig[i]) {
                if (!unstableTPSCheck) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("daem0ns.perfreport")) {
                            p.sendMessage(colorMsg("&8[&7daem&80&7ns&8] &cThe TPS is critically low, check the console for more information."));
                        }
                    }
                    log.warning(colorMsg("&8[&7daem&80&7ns&8] &cServer is lagging, running TPS report..."));
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tps");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timings report");
                    unstableTPSCheck = true;
                }
            } else {
                if (Main.getInstance().getConfig().getBoolean("tps.console-messages"))
                    log.info(colorMsg("&8[&7daem&80&7ns&8] Server is running normally."));
                unstableTPSCheck = false;
            }
        }
    }

}
