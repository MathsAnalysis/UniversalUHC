package me.mathanalysis.it.uhc.team;

import com.mongodb.client.model.FindOneAndReplaceOptions;
import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import net.md_5.bungee.api.chat.*;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TeamManager {

    private List<Team> teams = new ArrayList<>();
    private Team team;
    private int index = 0;

    public TeamManager(){
        if (UniversalUHC.get().getGameManager().getGameData().isTeam()){
        }
    }


    public void RandomTeam(){
        //TODO: Random team if is starting a team game


    }

    public void createTeam(Player player){
        index++;
        String prefix = team.getChatColor() + "[" + this.index + "]";
        this.team = new Team(
                player.getUniqueId(),
                player.getName(),
                UUID.randomUUID().toString(),
                prefix,
                UniversalUHC.get().getGameManager().getGameData().getTeamSize(),
                UniversalUHC.get().getGameManager().getGameData().isFriendlyFire(),
                new ArrayList<>(List.of(player.getUniqueId()))
        );
        teams.add(team);
    }


    public void removeTeam(Player player){
        teams.forEach(team -> team.disband(player.getUniqueId()));
    }

    public void invitePlayer(Player player, Player target){
        if (target == null){
            player.sendMessage("Player not found");
            return;
        }

        team.getInvites().put(target.getUniqueId(), Instant.now().plusSeconds(60));

        if (team.getInvites().containsKey(target.getUniqueId())){

            team.getMembers().forEach(member->{
                Player memberPlayer = Bukkit.getPlayer(member);
                memberPlayer.sendMessage("&9" + target.getName() + " &7has been invited to the team");
            });


            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("&9Click to accept the invite").create());
            ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team join " + team.getLeader().toString());

            TextComponent component = new TextComponent("You have been invited to " + player.getName() + "'s team");
            component.setHoverEvent(hoverEvent);
            component.setClickEvent(clickEvent);

            target.spigot().sendMessage(component);
        }

        if (team.getInvites().get(target.getUniqueId()).isBefore(Instant.now())){
            team.getInvites().remove(target.getUniqueId());
            player.sendMessage("&7The invite has expired for " + target.getName());
            target.sendMessage("&7The invite has expired for " + player.getName());
        }

        if (team.getMembers().size() == UniversalUHC.get().getGameManager().getGameData().getTeamSize()){
            player.sendMessage("&7The team is full");
            return;
        }

        if (team.getMembers().contains(target.getUniqueId())){
            player.sendMessage("&7The player is already in the team");
            return;
        }

        if (team.getMembers().size() < UniversalUHC.get().getGameManager().getGameData().getTeamSize()){
            team.getMembers().add(target.getUniqueId());
            team.getInvites().remove(target.getUniqueId());
            player.sendMessage("&9" + target.getName() + " &7has joined the team");
            target.sendMessage("&7You have joined " + player.getName() + "'s team");
        }
    }






}
