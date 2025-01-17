package net.kaijane.serverSpawn;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Bukkit;

public class PlayerJoinListener implements Listener {

    private Location spawnLocation;

    public PlayerJoinListener(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (spawnLocation != null) {
            Bukkit.getLogger().info("Teleporting " + event.getPlayer().getName() + " to " + spawnLocation);
            event.getPlayer().teleport(spawnLocation);
        }
    }
}