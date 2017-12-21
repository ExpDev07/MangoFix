package me.idarkyy.mango.fix;

import me.idarkyy.mango.fix.api.ConfigAPI;
import me.idarkyy.mango.fix.api.FactionsAPI;
import me.idarkyy.mango.fix.commands.MangoCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MangoFix extends JavaPlugin {
    public static MangoFix instance;
    public static MangoFix getInstance() { return instance; }

    /*
    public static FactionsAPI FAPI;
    public static FactionsAPI getFactionsAPI() { return FAPI; }

    public static FactionsAPI CAPI;
    public static FactionsAPI getConfigAPI() { return CAPI; }
    */


    @Override
    public void onEnable() {
        instance = this;
        FactionsAPI.API = new FactionsAPI();
        ConfigAPI.API = new ConfigAPI();

        String lines = "&8&m-----------------------------------------------------";
        log(lines);
        log("&b&lMangoFix &7By &biDarkyy");
        log("");
        log("&bMangoFix Version: &7" + getDescription().getVersion());
        log("&bMango Version: &7" + Bukkit.getPluginManager().getPlugin("Mango").getDescription().getVersion());
        log(lines);
        loadCommands();
        ConfigAPI.getAPI().loadConfiguration();

        new BukkitRunnable() {
            @Override
            public void run() {
                FactionsAPI.getAPI().saveFactions();
            }
        }.runTaskTimer(this, 0L, 1200);
    }

    @Override
    public void onDisable() {
        FactionsAPI.getAPI().saveFactions();
    }

    private static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(c(message));
    }
    private static String c(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    protected void loadCommands() {
        getCommand("mango").setExecutor(new MangoCommand());
    }
    protected void loadConfig() {

    }
}
