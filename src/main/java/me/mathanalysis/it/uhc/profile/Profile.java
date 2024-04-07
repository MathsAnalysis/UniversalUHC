package me.mathanalysis.it.uhc.profile;

import com.google.common.collect.Maps;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Data;
import lombok.Getter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.Tasks;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

@Data
public class Profile {

    @Getter public static Map<UUID, Profile> profiles = Maps.newHashMap();

    private UUID uuid;
    private String name, displayName;
    private String firstJoin, lastJoin;

    private boolean uhcHost = false, uhcMod = false, spectator = false;

    public Profile(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
        this.displayName = name;
        profiles.put(uuid, this);
        load();
    }

    public Profile(UUID uuid){
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid) != null? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
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

    public static Profile getProfile(UUID uuid){
        Profile profile = profiles.get(uuid);

        if (profile == null){
            profile = new Profile(uuid);
        }

        return profile;
    }

    public static Profile getProfile(String name){
        return profiles.values().stream().filter(profile -> profile.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isSpectator() {
        return spectator || uhcMod || uhcHost;
    }

    public boolean isStaff(){
        return uhcMod || uhcHost;
    }
}
