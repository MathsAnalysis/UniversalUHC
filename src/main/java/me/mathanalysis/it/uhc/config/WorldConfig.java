package me.mathanalysis.it.uhc.config;


import io.github.thatsmusic99.configurationmaster.CMFile;
import me.mathanalysis.it.uhc.UniversalUHC;

public class WorldConfig extends CMFile {

    public WorldConfig() {
        super(UniversalUHC.get().getPlugin(), "worlds");
        load();
    }

    @Override
    public void loadDefaults() {

        addSection("Normal World Settings");
        addComment("Normal World Settings", "This is the settings for the normal world");
        addDefault("Worlds.uhc_world.name", "uhc_world");
        addDefault("Worlds.uhc_world.type", "NORMAL");
        addDefault("Worlds.uhc_world.center.x", "0");
        addDefault("Worlds.uhc_world.center.z", "0");
        addDefault("Worlds.uhc_world.size", "1000");

        addSection("Practice World Settings");
        addComment("Practice World Settings", "This is the settings for the practice world");
        addDefault("Worlds.uhc_practice.name", "uhc_practice");
        addDefault("Worlds.uhc_practice.type", "NORMAL");
        addDefault("Worlds.uhc_practice.center.x", "0");
        addDefault("Worlds.uhc_practice.center.z", "0");
        addDefault("Worlds.uhc_practice.size", "1000");

        addSection("Nether World Settings");
        addComment("Nether World Settings", "This is the settings for the nether world");
        addDefault("Worlds.uhc_nether.name", "uhc_nether");
        addDefault("Worlds.uhc_nether.type", "NETHER");
        addDefault("Worlds.uhc_nether.center.x", "0");
        addDefault("Worlds.uhc_nether.center.z", "0");
        addDefault("Worlds.uhc_nether.size", "500");

        addSection("End World Settings");
        addComment("End World Settings", "This is the settings for the end world");
        addDefault("Worlds.uhc_end.name", "uhc_end");
        addDefault("Worlds.uhc_end.type", "END");
        addDefault("Worlds.uhc_end.center.x", "0");
        addDefault("Worlds.uhc_end.center.z", "0");
        addDefault("Worlds.uhc_end.size", "100");
    }
}
