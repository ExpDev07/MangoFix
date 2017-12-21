package me.idarkyy.mango.fix.api;

import me.idarkyy.mango.fix.MangoFix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.zencode.mango.Mango;
import org.zencode.mango.factions.Faction;
import java.io.IOException;

public class FactionsAPI {
    public static FactionsAPI API;
    public static FactionsAPI getAPI() {
        return API;
    }
    public void saveFactions() {
        try {
            if (Mango.getInstance().getFactionManager() == null) {
                return;
            }
            for (Faction faction : Mango.getInstance().getFactionManager().getFactions()) {
                try {
                    faction.save();
                } catch (IOException | NullPointerException e) {
                    log("&8(&4Error&8) &7Could not save faction &c" + faction.getName() + " &8(&cType: &7" + faction.getClass().getSimpleName() + "&8)");
                }
            }
        } catch(Exception e) {
            if(ConfigAPI.getAPI().getConfig().getBoolean("debug", false)) {
                log("&bException:\n&b" + e.getMessage());
            }
            log("&8(&bMango&8) &7Could not execute method &bFactionsAPI#saveFactions&7!");
        }
    }
    private static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(c(message));
    }
    private static String c(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}