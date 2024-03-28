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

        addDefault("Worlds.World1", "World1");
        addDefault("test", "test1", "Testing 1, testing 2!");

        addSection("Important Stuff");
        addComment("Important Stuff", "This is the important stuff");
        addComment("World", "This is the nether world");
        addDefault("Nether.World", "World_nether");
        addDefault("Nether.Environment", "NETHER");
        addDefault("Nether.Seed", 123456789);


    }
}
