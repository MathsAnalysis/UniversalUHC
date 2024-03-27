package me.mathanalysis.it.uhc;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.database.MongoManager;
import net.xconfig.bukkit.model.SimpleConfigurationManager;
import net.xconfig.bukkit.model.config.ConfigurationManager;
import org.bukkit.Bukkit;

@Getter
@Setter
public class UniversalUHC {

    private static UniversalUHC instance;
    private UniversalUHCPlugin plugin = UniversalUHCPlugin.get();

    private MongoManager mongoManager;


    private  


    public void init(){
        instance = this;

        loadConfig();
        loadDatabase();
        loadManager();
        loadListener();
        loadHook();
        loadOther();
    }

    public void shutdown(){


    }

    public void loadConfig(){
    }

    public void loadListener(){

    }

    public void loadManager(){
    }

    public void loadDatabase(){
        if (!this.mongoManager.isConnected()){
            this.mongoManager = new MongoManager();
            this.mongoManager.setConnected(true);
            String connected = (this.mongoManager.isConnected() ? "true" : "false");
            Bukkit.getLogger().info("\n&a&lConnected to MongoDB\n Status: " + connected + "\n");
        }
    }

    private void loadHook(){

    }

    private void loadOther(){

    }

    public static UniversalUHC get(){
        return instance;
    }

}
