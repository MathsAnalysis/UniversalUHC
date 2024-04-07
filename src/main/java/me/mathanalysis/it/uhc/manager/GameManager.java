package me.mathanalysis.it.uhc.manager;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.game.GameData;

@Getter
@Setter
public class GameManager {

    private GameData gameData;

    public GameManager(){
        this.gameData = new GameData();
    }

}
