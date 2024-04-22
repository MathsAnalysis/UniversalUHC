package me.mathanalysis.it.uhc.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import me.mathanalysis.it.uhc.utils.CC;
import org.bson.Document;
import org.bukkit.Bukkit;

@Data
public class MongoManager {

    public static boolean connected = false;
    private static MongoManager instance;

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> profiles, stats, kitsPractice;
    private MongoClientSettings settings;

    public MongoManager(String URI, String database){

        this.settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(URI))
                .retryReads(true)
                .retryWrites(true)
                .build();

        this.client = MongoClients.create(settings);
        this.database = this.client.getDatabase(database);
        this.profiles = this.database.getCollection("profile");
        this.stats = this.database.getCollection("statistics"); //stats
        this.kitsPractice = this.database.getCollection("kitsPractice");
    }


    public void close(){
        if (this.client != null){
            this.client.close();
        }
    }

    public static MongoManager get(String URI, String database){
        if(instance == null){
            instance = new MongoManager(URI, database);
            connected = true;

        }

        return instance;
    }


    public static void logSendMessage(){
        String status = connected ? "&aConnesso" : "&cDisconnesso";
        Bukkit.getLogger().info("");
        Bukkit.getLogger().info(CC.translate("&5&lConnessione al database: &f&lMongoDB"));
        Bukkit.getLogger().info(CC.translate("&dStato: &7" + status));
        Bukkit.getLogger().info("");
    }



}
