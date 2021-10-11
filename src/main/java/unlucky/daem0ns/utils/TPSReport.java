package unlucky.daem0ns.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import unlucky.daem0ns.Main;

import java.util.logging.Level;


public class TPSReport extends BukkitRunnable {
    private final JavaPlugin plugin;

    private boolean unstableTPSCheck = false;

    public TPSReport(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        double[] tps = Bukkit.getTPS();
        if (tps[0] <= Main.getInstance().getConfig().getDouble("tps.1m") || tps[1] <= Main.getInstance().getConfig().getDouble("tps.5m") || tps[2] <= Main.getInstance().getConfig().getDouble("tps.15m")) {
            if (!unstableTPSCheck) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("daem0ns.perfreport")) {
                        p.sendMessage(Chat.colorMsg("&8[&7daem&80&7ns&8] &cThe TPS is critically low, check the console for more information."));
                    }
                }
                Bukkit.getLogger().log(Level.WARNING, Chat.colorMsg("&8[&7daem&80&7ns&8] &cServer is lagging, running TPS report..."));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tps");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timings report");
                unstableTPSCheck = true;
            }
        } else {
            if (Main.getInstance().getConfig().getBoolean("tps.console-messages"))
                Bukkit.getLogger().log(Level.INFO, Chat.colorMsg("&8[&7daem&80&7ns&8] Server is running normally."));
            unstableTPSCheck = false;
        }
    }
}
