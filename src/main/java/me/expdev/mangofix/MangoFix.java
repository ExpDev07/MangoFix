package me.expdev.mangofix;

import me.expdev.mangofix.api.FactionsAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MangoFix extends JavaPlugin {

    private static MangoFix instance;

    public static MangoFix getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        FactionsAPI.API = new FactionsAPI();

        long ticks = 20 * 60 * 5L;
        new BukkitRunnable() {
            @Override
            public void run() {
                FactionsAPI.getAPI().saveFactions();
            }
        }.runTaskTimer(this, ticks, ticks);
    }

    @Override
    public void onDisable() {
        FactionsAPI.getAPI().saveFactions();
    }

}
