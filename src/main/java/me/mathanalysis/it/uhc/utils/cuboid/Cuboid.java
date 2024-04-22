package me.mathanalysis.it.uhc.utils.cuboid;

import it.unimi.dsi.fastutil.Pair;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cuboid {

    private final String worldName;
    private final int minX;
    private final int minY;
    private final int minZ;
    private final int maxX;
    private final int maxY;
    private final int maxZ;

    public Cuboid(Cuboid other) {
        this(other.getWorld().getName(), other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
    }

    public Cuboid(String worldName, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.worldName = worldName;

        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxY = Math.max(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxZ = Math.max(z1, z2);
    }

    public Cuboid(Location first, Location second) {
        this.worldName = first.getWorld().getName();

        this.minX = Math.min(first.getBlockX(), second.getBlockX());
        this.minY = Math.min(first.getBlockY(), second.getBlockY());
        this.minZ = Math.min(first.getBlockZ(), second.getBlockZ());
        this.maxX = Math.max(first.getBlockX(), second.getBlockX());
        this.maxY = Math.max(first.getBlockY(), second.getBlockY());
        this.maxZ = Math.max(first.getBlockZ(), second.getBlockZ());
    }

    public Vector getMin() {
        return new Vector(this.minX, this.minY, this.minZ);
    }

    public Vector getMax() {
        return new Vector(this.maxX, this.maxY, this.maxZ);
    }

    public List<Pair<Integer, Integer>> getChunks() {
        List<Pair<Integer, Integer>> chunks = new ArrayList<>();

        for(int x = this.minX >> 4; x <= this.maxX >> 4; x++) {
            for(int z = this.minZ >> 4; z <= this.maxZ >> 4; z++) {
                chunks.add(Pair.of(x, z));
            }
        }

        return chunks;
    }

    List<Location> edges(Location loc) {
        List<Location> closestEdges = new ArrayList<>();

        int closestX = this.closestX(loc);
        int closestZ = this.closestZ(loc);

        for(int x = Math.max(this.minX, loc.getBlockX() - 5); x <= Math.min(this.maxX, loc.getBlockX() + 5); x++) {
            closestEdges.add(new Location(loc.getWorld(), x, loc.getBlockY(), closestZ));
        }

        for(int z = Math.max(this.minZ, loc.getBlockZ() - 5); z <= Math.min(this.maxZ, loc.getBlockZ() + 5); z++) {
            closestEdges.add(new Location(loc.getWorld(), closestX, loc.getBlockY(), z));
        }

        return closestEdges;
    }

    public World getWorld() {
        return Bukkit.getWorld(this.worldName);
    }

    private int closestX(Location loc) {
        return Math.abs(loc.getBlockX() - this.minX) < Math.abs(loc.getBlockX() - this.maxX) ? this.minX : this.maxX;
    }

    private int closestZ(Location loc) {
        return Math.abs(loc.getBlockZ() - this.minZ) < Math.abs(loc.getBlockZ() - this.maxZ) ? this.minZ : this.maxZ;
    }

    public Cuboid inset(CuboidDirection dir, int amount) {
        return outset(dir, -amount);
    }

    public Cuboid outset(CuboidDirection dir, int amount) {
        return switch (dir) {
            case HORIZONTAL -> expand(CuboidDirection.NORTH, amount).expand(CuboidDirection.SOUTH, amount).expand(CuboidDirection.EAST, amount).expand(CuboidDirection.WEST, amount);
            case VERTICAL -> expand(CuboidDirection.DOWN, amount).expand(CuboidDirection.UP, amount);
            case BOTH -> outset(CuboidDirection.HORIZONTAL, amount).outset(CuboidDirection.VERTICAL, amount);
            default -> throw new IllegalArgumentException("invalid direction " + dir);
        };
    }

    public Cuboid expand(CuboidDirection dir, int amount) {
        return switch (dir) {
            case NORTH -> new Cuboid(worldName, minX - amount, minY, minZ, maxX, maxY, maxZ);
            case SOUTH -> new Cuboid(worldName, minX, minY, minZ, maxX + amount, maxY, maxZ);
            case EAST -> new Cuboid(worldName, minX, minY, minZ - amount, maxX, maxY, maxZ);
            case WEST -> new Cuboid(worldName, minX, minY, minZ, maxX, maxY, maxZ + amount);
            case DOWN -> new Cuboid(worldName, minX, minY - amount, minZ, maxX, maxY, maxZ);
            case UP -> new Cuboid(worldName, minX, minY, minZ, maxX, maxY + amount, maxZ);
            default -> throw new IllegalArgumentException("invalid direction " + dir);
        };
    }

    public Cuboid clone() {
        return new Cuboid(this);
    }
}
