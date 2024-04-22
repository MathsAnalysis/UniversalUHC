package me.mathanalysis.it.uhc.world.utility;

import lombok.experimental.UtilityClass;
import me.mathanalysis.it.uhc.UniversalUHC;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

@UtilityClass
public class WorldUtility {

    private final UniversalUHC plugin = UniversalUHC.get();

    public void createOverWorld(){
        plugin.getWorldManager().setUhcWorldSize(plugin.getWorldFile().getInt("Worlds.uhc_world.border"));
        plugin.getGameManager().getGameData().setBorder(plugin.getWorldManager().getUhcWorldSize());

        plugin.getWorldManager().setUhcWorld(WorldCreator.name(plugin.getWorldFile().getString("Worlds.uhc_world.name"))
                .environment(World.Environment.NORMAL)
                .type(WorldType.NORMAL)
                .generateStructures(false)
                .createWorld());

        int xCenter = plugin.getWorldFile().getInt("Worlds.uhc_world.center.x");
        int zCenter = plugin.getWorldFile().getInt("Worlds.uhc_world.center.z");

        plugin.getWorldManager().getUhcWorld().getWorldBorder().setSize(plugin.getWorldManager().getUhcWorldSize());
        plugin.getWorldManager().getUhcWorld().getWorldBorder().setCenter(xCenter, zCenter);

        plugin.getWorldManager().getUhcWorld().getWorldBorder().setDamageAmount(0);
        plugin.getWorldManager().getUhcWorld().getWorldBorder().setDamageBuffer(0);
        plugin.getWorldManager().getUhcWorld().getWorldBorder().setWarningDistance(0);
        plugin.getWorldManager().getUhcWorld().getWorldBorder().setWarningTime(0);

        plugin.getWorldManager().getUhcWorld().setGameRuleValue("doDaylightCycle", "false");
        plugin.getWorldManager().getUhcWorld().setGameRuleValue("doMobSpawning", "false");
        plugin.getWorldManager().getUhcWorld().setGameRuleValue("doFireTick", "false");
        plugin.getWorldManager().getUhcWorld().setGameRuleValue("naturalRegeneration", "false");
        plugin.getWorldManager().getUhcWorld().setTime(1000);
        plugin.getWorldManager().getUhcWorld().getEntities().clear();
        plugin.getWorldManager().getUhcWorld().setDifficulty(Difficulty.HARD);
        plugin.getWorldManager().getUhcWorld().setPVP(false);
        plugin.getWorldManager().getUhcWorld().setStorm(false);
        plugin.getWorldManager().getUhcWorld().setThundering(false);


        int xSpawn = plugin.getWorldFile().getInt("Worlds.uhc_world.spawnLocation.x");
        int ySpawn = plugin.getWorldFile().getInt("Worlds.uhc_world.spawnLocation.y");
        int zSpawn = plugin.getWorldFile().getInt("Worlds.uhc_world.spawnLocation.z");
        plugin.getWorldManager().getUhcWorld().setSpawnLocation(xSpawn, ySpawn, zSpawn);
    }

    public void createPracticeWorld(){
        plugin.getWorldManager().setUhcPracticeWorldSize(plugin.getWorldFile().getInt("Worlds.uhc_practice.border"));

        plugin.getWorldManager().setUhcPracticeWorld(WorldCreator.name(plugin.getWorldFile().getString("Worlds.uhc_practice.name"))
                .environment(World.Environment.NORMAL)
                .type(WorldType.NORMAL)
                .generateStructures(false)
                .createWorld());

        int xCenter = plugin.getWorldFile().getInt("Worlds.uhc_world.center.x");
        int zCenter = plugin.getWorldFile().getInt("Worlds.uhc_world.center.z");

        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setSize(plugin.getWorldManager().getUhcPracticeWorldSize());
        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setCenter(xCenter, zCenter);

        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setDamageAmount(0);
        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setDamageBuffer(0);
        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setWarningDistance(0);
        plugin.getWorldManager().getUhcPracticeWorld().getWorldBorder().setWarningTime(0);

        plugin.getWorldManager().getUhcPracticeWorld().setGameRuleValue("doDaylightCycle", "false");
        plugin.getWorldManager().getUhcPracticeWorld().setGameRuleValue("doMobSpawning", "false");
        plugin.getWorldManager().getUhcPracticeWorld().setGameRuleValue("doFireTick", "false");
        plugin.getWorldManager().getUhcPracticeWorld().setGameRuleValue("naturalRegeneration", "false");
        plugin.getWorldManager().getUhcPracticeWorld().setTime(1000);
        plugin.getWorldManager().getUhcPracticeWorld().getEntities().clear();
        plugin.getWorldManager().getUhcPracticeWorld().setDifficulty(Difficulty.HARD);
        plugin.getWorldManager().getUhcPracticeWorld().setPVP(false);
        plugin.getWorldManager().getUhcPracticeWorld().setStorm(false);
        plugin.getWorldManager().getUhcPracticeWorld().setThundering(false);


        int xSpawn = plugin.getWorldFile().getInt("Worlds.uhc_practice.spawnLocation.x");
        int ySpawn = plugin.getWorldFile().getInt("Worlds.uhc_practice.spawnLocation.y");
        int zSpawn = plugin.getWorldFile().getInt("Worlds.uhc_practice.spawnLocation.z");
        plugin.getWorldManager().getUhcPracticeWorld().setSpawnLocation(xSpawn, ySpawn, zSpawn);
    }

    public void createNetherWorld(){
        if (!plugin.getWorldFile().getBoolean("Worlds.uhc_nether.enabled")) return;
        plugin.getWorldManager().setUhcNetherworldSize(plugin.getWorldFile().getInt("Worlds.uhc_nether.border"));
        plugin.getGameManager().getGameData().setBorder(plugin.getWorldManager().getUhcNetherworldSize());


        plugin.getWorldManager().setUhcNetherWorld(WorldCreator.name(plugin.getWorldFile().getString("Worlds.uhc_nether.name"))
                .environment(World.Environment.NETHER)
                .type(WorldType.NORMAL)
                .generateStructures(false)
                .createWorld());

        int xCenter = plugin.getWorldFile().getInt("Worlds.uhc_nether.center.x");
        int zCenter = plugin.getWorldFile().getInt("Worlds.uhc_nether.center.z");

        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setSize(plugin.getWorldManager().getUhcNetherworldSize());
        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setCenter(xCenter, zCenter);

        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setDamageAmount(0);
        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setDamageBuffer(0);
        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setWarningDistance(0);
        plugin.getWorldManager().getUhcNetherWorld().getWorldBorder().setWarningTime(0);

        plugin.getWorldManager().getUhcNetherWorld().setGameRuleValue("doDaylightCycle", "false");
        plugin.getWorldManager().getUhcNetherWorld().setGameRuleValue("doMobSpawning", "false");
        plugin.getWorldManager().getUhcNetherWorld().setGameRuleValue("doFireTick", "false");
        plugin.getWorldManager().getUhcNetherWorld().setGameRuleValue("naturalRegeneration", "false");
        plugin.getWorldManager().getUhcNetherWorld().setTime(1000);
        plugin.getWorldManager().getUhcNetherWorld().getEntities().clear();
        plugin.getWorldManager().getUhcNetherWorld().setDifficulty(Difficulty.HARD);
        plugin.getWorldManager().getUhcNetherWorld().setPVP(false);
        plugin.getWorldManager().getUhcNetherWorld().setStorm(false);
        plugin.getWorldManager().getUhcNetherWorld().setThundering(false);

        int xSpawn = plugin.getWorldFile().getInt("Worlds.uhc_nether.spawnLocation.x");
        int ySpawn = plugin.getWorldFile().getInt("Worlds.uhc_nether.spawnLocation.y");
        int zSpawn = plugin.getWorldFile().getInt("Worlds.uhc_nether.spawnLocation.z");
        plugin.getWorldManager().getUhcNetherWorld().setSpawnLocation(xSpawn, ySpawn, zSpawn);
    }


}
