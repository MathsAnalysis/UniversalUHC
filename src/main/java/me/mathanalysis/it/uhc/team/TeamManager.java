package me.mathanalysis.it.uhc.team;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.game.GameData;
import me.mathanalysis.it.uhc.state.GameState;
import me.mathanalysis.it.uhc.utils.CC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TeamManager {

    private List<Team> teams = new ArrayList<>();
    private Team team;
    private int index = 0;

    public TeamManager(){

    }


    public void RandomTeam(){
        //TODO: Random team if is starting a team game
        GameData gameData = UniversalUHC.get().getGameManager().getGameData();

        if((gameData.getGameState() == GameState.STARTING) || (gameData.isTeam() && gameData.getTeamSize() > 1)){
            this.playerWithOutTeam().forEach(this::createTeam);
        }
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


            HoverEvent acceptHoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("&dClicca per accettare!").create());
            ClickEvent acceptClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team join " + team.getLeader().toString());

            TextComponent accept = new TextComponent(CC.translate("&a&l[ACCETTA]"));
            accept.setHoverEvent(acceptHoverEvent);
            accept.setClickEvent(acceptClickEvent);

            HoverEvent refuseHoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("&dClicca per rifiutare!").create());
            ClickEvent refuseClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team reject " + team.getLeader().toString());

            TextComponent refuse = new TextComponent(CC.translate("&c&l[RIFIUTA]"));
            refuse.setHoverEvent(refuseHoverEvent);
            refuse.setClickEvent(refuseClickEvent);


            String message =
                    "\n"
                    + "&d&lTEAM INVITE\n"
                    + "&d" + target.getName() + " &7ti ha invitato nel team\n"
                    + "[" + team.getMembers().size() + "/" + team.getMembers().size() + "]\n"
                    + "[" + Bukkit.getPlayer(team.getLeader()).getName() + "] " + "\n"
                    + "&7Clicca su\n"
                    + accept + "\n"
                    + "&7 per accettare o\n"
                    + refuse + "\n"
                    + "&7 per rifiutare"
                    + "\n";
            player.sendMessage(CC.translate(message));
        }

        if (team.getInvites().get(target.getUniqueId()).isBefore(Instant.now())){
            team.getInvites().remove(target.getUniqueId());
            player.sendMessage(CC.translate(CC.PREFIX + "&7L'invito è scaduto per " + target.getName()));
            target.sendMessage(CC.translate(CC.PREFIX + "&7L'invito è scaduto per " + player.getName()));
        }

        if (team.getMembers().size() == UniversalUHC.get().getGameManager().getGameData().getTeamSize()){
            player.sendMessage(CC.translate(CC.PREFIX + "&cIl team è pieno"));
            return;
        }

        if (team.getMembers().contains(target.getUniqueId())){
            player.sendMessage(CC.translate(CC.PREFIX + "&7Il giocatore è già nel team"));
            return;
        }

        if (team.getMembers().size() < UniversalUHC.get().getGameManager().getGameData().getTeamSize()){
            team.getMembers().add(target.getUniqueId());
            team.getInvites().remove(target.getUniqueId());
            player.sendMessage(CC.translate(CC.PREFIX + "&d" + target.getName() + " &7è entrato nel team"));
            target.sendMessage(CC.translate(CC.PREFIX + "&7Sei entrato nel team di &d" + player.getName()));
        }
    }


    public List<Player> playerWithOutTeam(){
        List<Player> players = new ArrayList<>();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (teams.stream().noneMatch(team -> team.getMembers().contains(player.getUniqueId()))){
                players.add(player);
            }
        });
        return players;
    }




}
