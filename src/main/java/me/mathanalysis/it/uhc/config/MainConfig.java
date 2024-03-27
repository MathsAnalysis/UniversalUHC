package me.mathanalysis.it.uhc.config;

import io.github.thatsmusic99.configurationmaster.CMFile;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.UniversalUHCPlugin;
import org.bukkit.plugin.Plugin;

public class MainConfig extends CMFile{

    public MainConfig() {
        super(UniversalUHC.get().getPlugin(), "config.yml");
        load();
    }

    @Override
    public void loadDefaults() {
        addSection("Database Settings");
        addComment("Database Settings", "This is the settings for the database");
        addDefault("Mongo.URI", "mongodb://localhost:27017");
        addDefault("Mongo.Database", "uhc");

    }
}
