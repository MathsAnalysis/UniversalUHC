package me.mathanalysis.it.uhc.config;

import io.github.thatsmusic99.configurationmaster.CMFile;
import me.mathanalysis.it.uhc.UniversalUHC;

public class MainConfig extends CMFile{

    public MainConfig() {
        super(UniversalUHC.get().getPlugin(), "config");
        load();
    }

    @Override
    public void loadDefaults() {
        addSection("Database Settings");
        addComment("Impostazioni Database", "Impostazioni del database");
        addDefault("Mongo.URI", "mongodb://localhost:27017");
        addDefault("Mongo.Database", "uhc");
        addDefault("Spawn", "");
        addDefault("Prefix", "&d&lOBS&f&lMC &8» ");

        save(true);
    }
}
