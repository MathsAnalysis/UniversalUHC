package me.mathanalysis.it.uhc.profile;

import com.google.common.collect.Maps;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Data;
import lombok.Getter;
import me.mathanalysis.it.uhc.UniversalUHC;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Data
public class UHCStats {

    @Getter public static Object2ObjectOpenHashMap<UUID, UHCStats> stats = new Object2ObjectOpenHashMap<>();

    private UUID uuid;
    private String name;

    private int kills, deaths,
            wins, bowHit,
            goldHeadEaten, goldenAppleEaten,
            rodHit, swordHit,
            played, elo = 1400;

    private int coalMined, ironMined,
            goldMined, diamondMined,
            emeraldMined, lapisMined,
            redstoneMined, quartzMined;

    private int gameID, gameKills, gameDiamondMined,
            gameGoldMined, gameIronMined,
            gameEmeraldMined, gameLapisMined,
            gameRedstoneMined, gameQuartzMined;

    private Object2BooleanOpenHashMap<String> achievements = new Object2BooleanOpenHashMap<>();

    public UHCStats(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        stats.put(uuid, this);
        load();
    }

    public UHCStats(UUID uuid){
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
        stats.put(uuid, this);
    }

    public void load(){
        CompletableFuture.runAsync(this::loadData);
    }


    public void loadData(){
        Document document = UniversalUHC.get().getMongoManager().getStats().find(Filters.eq("_id", this.uuid.toString())).first();

        if (document == null){
            this.save();
            return;
        }

        if (document.getString("name") == null){
            document.put("name", this.name);
        }else {
            this.name = document.getString("name");
        }

        this.kills = document.getInteger("kills");
        this.deaths = document.getInteger("deaths");
        this.wins = document.getInteger("wins");
        this.bowHit = document.getInteger("bowHit");
        this.goldHeadEaten = document.getInteger("goldHeadEaten");
        this.goldenAppleEaten = document.getInteger("goldenApple");
        this.rodHit = document.getInteger("rodHit");
        this.swordHit = document.getInteger("swordHit");
        this.played = document.getInteger("played");
        this.elo = document.getInteger("elo");
        this.coalMined = document.getInteger("coalMined");
        this.ironMined = document.getInteger("ironMined");
        this.goldMined = document.getInteger("goldMined");
        this.diamondMined = document.getInteger("diamondMined");
        this.emeraldMined = document.getInteger("emeraldMined");
        this.lapisMined = document.getInteger("lapisMined");
        this.redstoneMined = document.getInteger("redstoneMined");
        this.quartzMined = document.getInteger("quartzMined");
    }

    public void save(){
        CompletableFuture.runAsync(this::saveData);
    }


    public void saveData(){
        UniversalUHC.get().getMongoManager().getStats().replaceOne(Filters.eq("_id", this.uuid.toString()),
                toDocument(),
                new ReplaceOptions().upsert(true)
        );
    }

public Document toDocument(){
        return new Document("_id", this.uuid.toString())
                .append("name", this.name)
                .append("kills", this.kills)
                .append("deaths", this.deaths)
                .append("wins", this.wins)
                .append("bowHit", this.bowHit)
                .append("goldHeadEaten", this.goldHeadEaten)
                .append("goldenApple", this.goldenAppleEaten)
                .append("rodHit", this.rodHit)
                .append("swordHit", this.swordHit)
                .append("played", this.played)
                .append("elo", this.elo)
                .append("coalMined", this.coalMined)
                .append("ironMined", this.ironMined)
                .append("goldMined", this.goldMined)
                .append("diamondMined", this.diamondMined)
                .append("emeraldMined", this.emeraldMined)
                .append("lapisMined", this.lapisMined)
                .append("redstoneMined", this.redstoneMined)
                .append("quartzMined", this.quartzMined);
    }

    public static UHCStats getStats(UUID uuid){
        UHCStats stats = getStats().get(uuid);

        if (stats == null){
            stats = new UHCStats(uuid);
        }

        return stats;
    }

    public static UHCStats getStats(String name){
        return getStats().values().stream().filter(stats -> stats.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public double getKdr() {
        return this.kills > 0 && this.deaths == 0 ? this.kills
                : this.kills == 0 && this.deaths == 0 ? 0.0
                : (double) this.kills / (double) this.deaths;
    }

    public double getKdr(Document document){
        return document.getInteger("kills") > 0 && document.getInteger("deaths") == 0 ? document.getInteger("kills")
                : document.getInteger("kills") == 0 && document.getInteger("deaths") == 0 ? 0.0
                : (double) document.getInteger("kills") / (double) document.getInteger("deaths");
    }
}
