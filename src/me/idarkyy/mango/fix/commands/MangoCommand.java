package me.idarkyy.mango.fix.commands;

import me.idarkyy.mango.fix.api.ConfigAPI;
import me.idarkyy.mango.fix.api.FactionsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MangoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String lines = "&8&m-----------------------------------------------------";
            if (player.hasPermission("mango.admin")) {
                if (strings.length == 0) {
                    player.sendMessage(c(lines));
                    player.sendMessage(c("&b&lMango Help:"));
                    player.sendMessage(c("  &8/&bmango forcesave &7Force save all factions"));
                    player.sendMessage(c("  &8/&bmango reload &7Reload the configuration"));
                    player.sendMessage(c(lines));
                } else {
                    if(strings[0].equalsIgnoreCase("forcesave")) {
                        long start = System.currentTimeMillis();
                        FactionsAPI.getAPI().saveFactions();
                        player.sendMessage(c("&8(&bMango&8) &7Saved all factions! &8(&b" + (System.currentTimeMillis() - start) + "&7ms&8)"));
                    } else if(strings[0].equalsIgnoreCase("reload") || strings[0].equalsIgnoreCase("rl")) {
                        long startedAt = System.currentTimeMillis();
                        ConfigAPI.getAPI().reloadConfig();
                        player.sendMessage(c("&8(&bMango&8) &7Reloaded the configuration! &8(&b" + (System.currentTimeMillis() - startedAt) + "&7ms&8)"));
                    }
                }
            }
        } else {
            String lines = "&8&m-----------------------------------------------------";
            if (strings.length == 0) {
                log(c(lines));
                log(c("&b&lMango Help:"));
                log(c("  &8/&bmango forcesave &7Force save all factions"));
                log(c("  &8/&bmango reload &7Reload the configuration"));
                log(c(lines));
            } else {
                if(strings[0].equalsIgnoreCase("forcesave")) {
                    long startedAt = System.currentTimeMillis();
                    FactionsAPI.getAPI().saveFactions();
                    log(c("&8(&bMango&8) &7Saved all factions! &8(&b" + (System.currentTimeMillis() - startedAt) + "&7ms&8)"));
                } else if(strings[0].equalsIgnoreCase("reload") || strings[0].equalsIgnoreCase("rl")) {
                    long startedAt = System.currentTimeMillis();
                    ConfigAPI.getAPI().reloadConfig();
                    log(c("&8(&bMango&8) &7Reloaded the configuration! &8(&b" + (System.currentTimeMillis() - startedAt) + "&7ms&8)"));
                }
            }
        }
        return false;
    }

    private static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(c(message));
    }
    private static String c(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
