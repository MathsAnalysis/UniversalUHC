package me.mathanalysis.it.uhc.world;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.Utility;
import me.mathanalysis.it.uhc.utils.cuboid.Cuboid;
import me.mathanalysis.it.uhc.world.utility.WorldUtility;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public class WorldManager {

    private static WorldManager INSTANCE;
    private UniversalUHC plugin = UniversalUHC.get();

    private World uhcWorld, uhcPracticeWorld, uhcNetherWorld;
    private int uhcWorldSize, uhcPracticeWorldSize, uhcNetherworldSize;
    private Cuboid uhcWorldCuboid, uhcNetherWorldCuboid, uhcPracticeWorldCuboid;

    public WorldManager() {
        if (INSTANCE != null) {
            throw new RuntimeException("WorldManager is already initialized");
        }

        INSTANCE = this;
        loadCreateMap();
    }

    public void loadCreateMap(){
        WorldUtility.createOverWorld();
        WorldUtility.createPracticeWorld();
        WorldUtility.createNetherWorld();
    }

    private void loadCuboid(){

        uhcWorldCuboid = new Cuboid(
                new Location(this.uhcWorld, this.uhcWorldSize, 0, -this.uhcWorldSize - 1),
                new Location(this.uhcWorld, -this.uhcWorldSize - 1, 0, this.uhcWorldSize)
        );

        uhcNetherWorldCuboid = new Cuboid(
                new Location(this.uhcNetherWorld, this.uhcNetherworldSize, 0, -this.uhcNetherworldSize - 1),
                new Location(this.uhcNetherWorld, -this.uhcNetherworldSize - 1, 0, this.uhcNetherworldSize)
        );

        uhcPracticeWorldCuboid = new Cuboid(
                new Location(this.uhcPracticeWorld, this.uhcPracticeWorldSize, 0, -this.uhcPracticeWorldSize - 1),
                new Location(this.uhcPracticeWorld, -this.uhcPracticeWorldSize - 1, 0, this.uhcPracticeWorldSize)
        );
    }

    public CompletableFuture<Location> getGenerate(World world, int size){
        return Utility.randomLocation(world, size);
    }

    public CompletableFuture<Location> getGenerateForPlayer(){
        return getGenerate(this.uhcWorld, this.uhcWorldSize);
    }


    public WorldManager get(){
        return INSTANCE;
    }
}
