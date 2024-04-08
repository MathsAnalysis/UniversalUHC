package me.mathanalysis.it.uhc.profile;

import com.google.common.collect.Maps;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Data;
import lombok.Getter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.state.PlayerState;
import me.mathanalysis.it.uhc.utils.Tasks;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

@Data
public class UHCProfile {

    @Getter public static Map<UUID, UHCProfile> profiles = Maps.newHashMap();

    private UUID uuid;
    private String name, displayName;
    private String firstJoin, lastJoin;
    private PlayerState playerState;

    private boolean
            uhcHost = false, uhcMod = false,
            spectator = false, practice = false;

    public UHCProfile(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        this.displayName = name;
        this.playerState = PlayerState.LOBBY;
        profiles.put(uuid, this);
        load();
    }

    public UHCProfile(UUID uuid){
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid) != null? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
        this.playerState = PlayerState.LOBBY;
        profiles.put(uuid, this);
    }

    public void load(){
        Tasks.asyncTask(this::loadData);
    }

    public void loadData(){
        Document document = UniversalUHC.get().getMongoManager().getProfiles().find(Filters.eq("_id", this.uuid.toString())).first();

        if (document == null){
            this.save();
            return;
        }

        this.name = document.getString("name");
        this.displayName = document.getString("displayName");
        this.firstJoin = document.getString("firstJoin");
        this.lastJoin = document.getString("lastJoin");
    }

    public void save(){
        Tasks.asyncTask(this::saveData);
    }

    public void saveData(){
        UniversalUHC.get().getMongoManager().getProfiles().replaceOne(Filters.eq("_id", this.uuid.toString()),
                toDocument(),
                new ReplaceOptions().upsert(true)
        );
    }

    public Document toDocument(){
        return new Document("_id", this.uuid.toString())
                .append("name", this.name)
                .append("displayName", this.displayName)
                .append("firstJoin", this.firstJoin)
                .append("lastJoin", this.lastJoin);
    }

    public static UHCProfile getProfile(UUID uuid){
        UHCProfile UHCProfile = profiles.get(uuid);

        if (UHCProfile == null){
            UHCProfile = new UHCProfile(uuid);
        }

        return UHCProfile;
    }

    public static UHCProfile getProfile(String name){
        return profiles.values().stream().filter(UHCProfile -> UHCProfile.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isSpectator() {
        return spectator || uhcMod || uhcHost;
    }

    public boolean isStaff(){
        return uhcMod || uhcHost;
    }

    public void setFirstJoin(LocalDateTime localDateTime) {
        Document document = UniversalUHC.get().getMongoManager().getProfiles().find(Filters.eq("_id", uuid.toString())).first();

        if (document == null || document.getString("firstJoin") == null || document.getString("firstJoin").isEmpty()){
            this.firstJoin = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            this.save();
            return;
        }

        this.firstJoin = document.getString("firstJoin");
    }


    public int daysDifference(){
        LocalDateTime firstJoin = LocalDateTime.parse(this.firstJoin, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LocalDateTime lastJoin = LocalDateTime.parse(this.lastJoin, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        long daysDifference = ChronoUnit.DAYS.between(lastJoin, firstJoin);
        return Math.toIntExact(daysDifference);
    }
}
