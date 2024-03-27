package me.mathanalysis.it.uhc;

import lombok.Getter;
import lombok.Setter;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class UniversalUHCPlugin extends JavaPlugin {

    private static UniversalUHCPlugin instance;

    private UniversalUHC universalUHC;

    @Override
    public void onEnable() {
        instance = this;



        this.universalUHC = new UniversalUHC();
        this.universalUHC.init();

    }

    @Override
    public void onDisable() {
        this.universalUHC.shutdown();
    }

    public static UniversalUHCPlugin get(){
        return instance;
    }
}
