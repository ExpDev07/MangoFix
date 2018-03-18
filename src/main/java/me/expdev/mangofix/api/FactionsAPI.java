package me.expdev.mangofix.api;

import org.bukkit.Bukkit;
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
                    Bukkit.getLogger().warning("Could not save faction " + faction.getName() + "  (Type: " + faction.getClass().getSimpleName() + ")");
                }
            }
        } catch (Exception e) {
            Bukkit.getLogger().warning("Could not execute method FactionsAPI#saveFactions()");
        }
    }
}