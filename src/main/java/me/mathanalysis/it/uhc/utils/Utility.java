package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import me.mathanalysis.it.uhc.UniversalUHC;
import org.bukkit.Location;
import org.bukkit.World;
import org.imanity.imanityspigot.chunk.AsyncPriority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@UtilityClass
public class Utility {

    private final int REDUCED_BORDER = 5;

    public String getDateFormatter(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public CompletableFuture<Location> randomLocation(World world, int radius) {
        int tries = 0;
        Random random = UniversalUHC.get().getRandom();
        int x = random.nextBoolean() ? random.nextInt(radius - REDUCED_BORDER) : -random.nextInt(radius + REDUCED_BORDER);
        int z = random.nextBoolean() ? random.nextInt(radius - REDUCED_BORDER) : -random.nextInt(radius + REDUCED_BORDER);
        int y = getHighestY(world, x, z);

        while (y < 55 && tries++ < 5) {
            x = random.nextBoolean() ? random.nextInt(radius - REDUCED_BORDER) : -random.nextInt(radius + REDUCED_BORDER);
            z = random.nextBoolean() ? random.nextInt(radius - REDUCED_BORDER) : -random.nextInt(radius + REDUCED_BORDER);
            y = getHighestY(world, x, z);
        }

        final int calculatedX = x;
        final int calculatedZ = z;
        return world.imanity().getChunkAtAsynchronously(calculatedX >> 4, calculatedZ >> 4, AsyncPriority.HIGHER)
                .thenApply((c) -> {
                    int calculatedY = world.getHighestBlockYAt(calculatedX, calculatedZ);
                    return LocationUtil.getHighest(new Location(world, calculatedX, calculatedY, calculatedZ));
                });
    }

    public int getHighestY(World world, int x, int z) {
        return world.getHighestBlockYAt(x, z);
    }
}
