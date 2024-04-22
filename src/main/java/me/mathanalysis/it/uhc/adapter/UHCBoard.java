package me.mathanalysis.it.uhc.adapter;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.CC;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UHCBoard {


    public UHCBoard(Player player){
        List<String> lines = new ArrayList<>();

        BPlayerBoard bPlayerBoard = Netherboard.instance().getBoard(player);


        switch (UniversalUHC.get().getGameManager().getGameData().getGameState()){
            case LOBBY->{
                 lines = new ArrayList<>(UniversalUHC.get().getScoreboardFile().getStringList("scoreboard.lobby.lines"));

            }
        }


        for (int i = 0; i < lines.size(); i++){
            int pos = lines.size()-1;
           // bPlayerBoard.set(CC.translate(PlaceholderAPI.setPlaceholders(player, board.get(i))), pos);
        }
    }

}
