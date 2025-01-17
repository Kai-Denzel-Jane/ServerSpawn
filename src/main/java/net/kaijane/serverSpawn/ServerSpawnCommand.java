package net.kaijane.serverSpawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerSpawnCommand implements CommandExecutor {

    private final PlayerJoinListener playerJoinListener;
    private final ServerSpawn plugin;

    public ServerSpawnCommand(PlayerJoinListener playerJoinListener, ServerSpawn plugin) {
        this.playerJoinListener = playerJoinListener;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 5 && args[0].equalsIgnoreCase("set")) {
                String worldName = args[1];
                double x, y, z;
                try {
                    x = Double.parseDouble(args[2]);
                    y = Double.parseDouble(args[3]);
                    z = Double.parseDouble(args[4]);
                } catch (NumberFormatException e) {
                    player.sendMessage("Coordinates must be numbers.");
                    return false;
                }

                Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
                if (location.getWorld() == null) {
                    player.sendMessage("World not found.");
                    return false;
                }

                playerJoinListener.setSpawnLocation(location);
                plugin.saveSpawnLocation(location);
                player.sendMessage("Login location set to " + location);
                return true;
            } else {
                player.sendMessage("Usage: /serverspawn set <world> <x> <y> <z>");
                return false;
            }
        }
        return false;
    }
}