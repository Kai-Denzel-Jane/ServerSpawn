package net.kaijane.serverSpawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerSpawn extends JavaPlugin {

    private PlayerJoinListener playerJoinListener;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Location spawnLocation = loadSpawnLocation();
        playerJoinListener = new PlayerJoinListener(spawnLocation);
        Bukkit.getPluginManager().registerEvents(playerJoinListener, this);
        this.getCommand("serverspawn").setExecutor(new ServerSpawnCommand(playerJoinListener, this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void saveSpawnLocation(Location location) {
        FileConfiguration config = getConfig();
        config.set("spawn.world", location.getWorld().getName());
        config.set("spawn.x", location.getX());
        config.set("spawn.y", location.getY());
        config.set("spawn.z", location.getZ());
        saveConfig();
    }

    public Location loadSpawnLocation() {
        FileConfiguration config = getConfig();
        String worldName = config.getString("spawn.world", "world");
        double x = config.getDouble("spawn.x", 0);
        double y = config.getDouble("spawn.y", 64);
        double z = config.getDouble("spawn.z", 0);
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }
}