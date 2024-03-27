
package me.mathanalysis.it.uhc;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.config.MainConfig;
import me.mathanalysis.it.uhc.config.WorldConfig;
import me.mathanalysis.it.uhc.database.MongoManager;
import me.mathanalysis.it.uhc.utils.Tasks;
import me.mathanalysis.it.uhc.world.WorldManager;

@Getter
@Setter
public class UniversalUHC {

    private static UniversalUHC instance;
    private UniversalUHCPlugin plugin = UniversalUHCPlugin.get();

    private MongoManager mongoManager;

    private MainConfig config;
    private WorldConfig worldConfig;

    private WorldManager worldManager;


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
        this.mongoManager.close();
        Tasks.cancelTasks();
        instance = null;
    }

    public void loadConfig(){
        this.config = new MainConfig();
        this.worldConfig = new WorldConfig();
    }

    public void loadListener(){

    }

    public void loadManager(){
        this.worldManager = new WorldManager();
    }

    public void loadDatabase(){
        this.mongoManager = MongoManager.get(config.getString("Mongo.URI"), config.getString("Mongo.Database"));
        MongoManager.logSendMessage();
    }

    private void loadHook(){

    }

    private void loadOther(){

    }

    public static UniversalUHC get(){
        return instance;
    }

}
