
package me.mathanalysis.it.uhc;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.command.manager.UHCCommandManager;
import me.mathanalysis.it.uhc.config.MainConfig;
import me.mathanalysis.it.uhc.database.MongoManager;
import me.mathanalysis.it.uhc.listener.DataListener;
import me.mathanalysis.it.uhc.listener.PlayerListener;
import me.mathanalysis.it.uhc.manager.GameManager;
import me.mathanalysis.it.uhc.manager.PlayerManager;
import me.mathanalysis.it.uhc.manager.item.ItemManager;
import me.mathanalysis.it.uhc.practice.PracticeManager;
import me.mathanalysis.it.uhc.team.TeamManager;
import me.mathanalysis.it.uhc.utils.ConfigFile;
import me.mathanalysis.it.uhc.utils.Tasks;
import me.mathanalysis.it.uhc.utils.workload.BlockLoadRunnable;
import me.mathanalysis.it.uhc.utils.workload.impl.DistributedFiller;
import me.mathanalysis.it.uhc.world.WorldManager;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;

import java.util.Random;
import java.util.stream.Stream;

@Getter
@Setter
public class UniversalUHC {

    private static UniversalUHC instance;
    private UniversalUHCPlugin plugin = UniversalUHCPlugin.get();

    private MongoManager mongoManager;

    private MainConfig config;

    private ConfigFile worldFile, messageFile, scoreboardFile;

    private WorldManager worldManager;
    private GameManager gameManager;
    private TeamManager teamManager;
    private PlayerManager playerManager;
    private ItemManager itemManager;
    private UHCCommandManager commandManager;
    private PracticeManager practiceManager;

    private BukkitAudiences audiences;


    private BlockLoadRunnable blockLoadRunnable;
    private DistributedFiller filler;

    private Random random = new Random();


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
        this.worldFile = new ConfigFile(plugin, "world", true, false);
        this.messageFile = new ConfigFile(plugin, "message", true, false);
        this.scoreboardFile = new ConfigFile(plugin, "scoreboard", true, true);
    }

    public void loadListener(){
        Stream.of(
                new DataListener(),
                new PlayerListener()
        ).forEach(listener -> plugin.getServer().getPluginManager().registerEvents(listener, plugin));
    }

    public void loadManager(){
        this.worldManager = new WorldManager();
        this.gameManager = new GameManager();
        this.teamManager = new TeamManager();
        this.playerManager = new PlayerManager();
        this.itemManager = new ItemManager();
        this.commandManager = new UHCCommandManager();
        this.practiceManager = new PracticeManager();
    }

    public void loadDatabase(){
        this.mongoManager = MongoManager.get(config.getString("Mongo.URI"), config.getString("Mongo.Database"));
        MongoManager.logSendMessage();
    }

    private void loadHook(){
        this.audiences = BukkitAudiences.create(plugin);
    }

    private void loadOther(){


        this.blockLoadRunnable = new BlockLoadRunnable();
        Bukkit.getScheduler().runTaskTimer(plugin, this.blockLoadRunnable, 1, 1);
        filler = new DistributedFiller(UniversalUHC.get().getBlockLoadRunnable());
    }

    public static UniversalUHC get(){
        return instance;
    }

}
