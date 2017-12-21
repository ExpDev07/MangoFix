package me.idarkyy.mango.fix.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.zencode.mango.Mango;
import org.zencode.mango.factions.claims.Claim;
import org.zencode.mango.factions.types.PlayerFaction;

public class BucketEmptyEvent implements Listener {
    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        if (event.getItemStack().getType() == Material.BUCKET) {
            Player player = event.getPlayer();
            PlayerFaction fac = Mango.getInstance().getFactionManager().getFaction(player);
            Claim claim = Mango.getInstance().getClaimManager().getClaimAt(event.getBlockClicked().getLocation());

            if ((claim != null) && (!claim.getOwner().equals(fac)) &&
                    (!player.hasPermission(Mango.getInstance().getConfigFile().getString("ADMIN_NODE")))) {
                event.setCancelled(true);

                player.sendMessage(Mango.getInstance().getLanguageFile().getString("FACTION_NO_INTERACT")
                        .replace("{faction}", claim.getOwner().getName()));
            }
        }
    }
}
