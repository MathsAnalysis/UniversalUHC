package me.mathanalysis.it.uhc.game;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GameData {

    private List<UUID> totalPlayersList = Lists.newArrayList();

    private int border, borderTime, borderShrink;
    private int graceTime, finalHeal;

    private int maxPlayers, minPlayers, teamSize;

    private int alivePlayers, scatteredPlayers, totalPlayers;

    private int time, endTime = 60;

    private boolean
            started = false, ended = false,
            finalHealEnabled = false, graceEnabled = false,
            friendlyFire = false, practice = false;

    private int autoStartTimer = 60, scatterTimer;

    private boolean
            league = false, tournament = false,
            ranked = false, team = false;

    public void startGame(boolean force){
        if (force){
            setStarted(true);
            setEnded(false);
            return;
        }


        if (totalPlayersList.size() >= minPlayers){
            setStarted(true);
            setEnded(false);
        }



    }


}
