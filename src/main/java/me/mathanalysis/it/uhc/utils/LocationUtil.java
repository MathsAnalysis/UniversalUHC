package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class LocationUtil {

    private final String[] FACES = {"S", "SW", "W", "NW", "N", "NE", "E", "SE"};

    public Location deserializeLocation(String string) {
        if (string == null) return null;
        String[] split = string.split(":");
        return new Location(Bukkit.getWorld(split[0]),
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]),
                Float.parseFloat(split[4]),
                Float.parseFloat(split[5]));
    }

    public String serializeLocation(Location location) {
        if (location == null) return null;
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
    }

    public boolean checkZone(Location location, int input) {
        return Math.abs(location.getBlockX()) <= input && Math.abs(location.getBlockZ()) <= input;
    }

    public void safeTeleport(Player player, Location location) {
        if (player.getVehicle() != null && player.getVehicle() instanceof Horse vehicle) {
            vehicle.eject();

            Tasks.runLater(() -> vehicle.teleport(location.add(0, 1, 0)), 1L);
            Tasks.runLater(() -> vehicle.setPassenger(player), 2L);
        } else {
            player.setFallDistance(0);
            player.teleport(location);
        }
    }

    public Location getScatterLocation(int border, String worldName) {
        int x = ThreadLocalRandom.current().nextInt(-border + 10, border - 10);
        int z = ThreadLocalRandom.current().nextInt(-border + 10, border - 10);

        World world = Bukkit.getWorld(worldName);
        Block block = world.getHighestBlockAt(x, z);
        Material relative = block.getRelative(BlockFace.DOWN).getType();

        if (block.getLocation().getY() < 40 || relative.name().endsWith("WATER") || relative.name().endsWith("LAVA")) {
            return getScatterLocation(border, worldName);
        }//vai

        return block.getLocation().add(0, 0.5, 0);
    }

    public void spawnHead(LivingEntity entity) {
        entity.getLocation().getBlock().setType(Material.NETHER_FENCE);
        entity.getWorld().getBlockAt(entity.getLocation().add(0.0D, 1.0D, 0.0D)).setType(Material.SKULL);

        Skull skull = (Skull) entity.getLocation().add(0.0D, 1.0D, 0.0D).getBlock().getState();

        if (entity instanceof Player player) {
            skull.setOwner(player.getName());
        } else {
            skull.setOwner(ChatColor.stripColor(entity.getCustomName()));
        }

        skull.update();

        Block block = entity.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
        block.setData((byte) 1);
    }

    public String getDirection(Player player) {
        return FACES[Math.round(player.getLocation().getYaw() / 45f) & 0x7];
    }

    public Location getHighest(Location location) {
        int x = location.getBlockX();
        int y = 256;
        int z = location.getBlockZ();

        while (y > location.getY()) {
            Block block = location.getWorld().getBlockAt(x, --y, z);
            if (!block.isEmpty() && !block.getType().toString().contains("LEAVES") && block.getType() != Material.AIR) {
                location.setX(location.getBlockX() + 0.5);
                location.setY(block.getLocation().getBlockY() + 1);
                location.setZ(location.getBlockZ() + 0.5);

                return location;
            }
        }

        return location;
    }
}