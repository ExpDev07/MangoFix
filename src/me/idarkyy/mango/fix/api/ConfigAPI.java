package me.idarkyy.mango.fix.api;

import me.idarkyy.mango.fix.MangoFix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigAPI {
    public static ConfigAPI API;
    public static ConfigAPI getAPI() {
        return API;
    }
    public FileConfiguration config = MangoFix.getInstance().getConfig();

    public void loadConfiguration() {
        config.addDefault("debug", false);
        config.options().copyDefaults(true);
        MangoFix.getInstance().saveConfig();
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public void reloadConfig() {
        try {
            MangoFix.getInstance().reloadConfig();
        } catch(Exception e) {
            if(ConfigAPI.getAPI().getConfig().getBoolean("debug", false)) {
                log("&bException:\n&b" + e.getMessage());
            }
            log("&8(&bMango&8) &7Could not execute method &bConfigAPI#reloadConfig&7!");
        }
    }
    private static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(c(message));
    }
    private static String c(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
