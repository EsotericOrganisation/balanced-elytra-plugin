package org.rolypolyvole.no_elytra_plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoElytraPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onRocketBoost(PlayerInteractEvent event) {
        boolean usedRocketInWorld =
            event.getMaterial().equals(Material.FIREWORK_ROCKET) &&
            event.getPlayer().isGliding() &&
            event.getPlayer().getWorld().getEnvironment().equals(World.Environment.NORMAL);

        if (usedRocketInWorld) {
            event.setCancelled(true);
        }
    }
}
