package me.mathanalysis.it.uhc.team;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Team {

    private UUID leader;
    private String name, id, prefix;
    private int teamSize;
    private boolean friendlyFire;
    private List<UUID> members;

    private Object2ObjectOpenHashMap<UUID, Instant> invites = new Object2ObjectOpenHashMap<>();

    public Team(UUID leader, String name, String id, String prefix, int teamSize, boolean friendlyFire, List<UUID> members){
        this.leader = leader;
        this.name = name;
        this.id = leader.toString();
        this.prefix = prefix;
        this.teamSize = teamSize;
        this.friendlyFire = friendlyFire;
        this.members = members;
    }

    public void disband(UUID player){
        this.members.remove(player);
        this.leader = null;
        this.name = null;
        this.id = null;
        this.prefix = null;
        this.teamSize = 0;
        this.friendlyFire = false;
        this.members.clear();
        this.invites.clear();
    }

    public ChatColor getChatColor(){
        ChatColor[] colors = ChatColor.values();
        return colors[new Random().nextInt(colors.length)];
    }

}
