package me.mathanalysis.it.uhc.practice;

import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.game.GameData;
import me.mathanalysis.it.uhc.practice.kit.KitEditorManager;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.Tasks;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
@Setter
public class PracticeManager {

    private Practice practice;
    private GameData gameData;

    private List<Player> practicePlayers;
    private int practiceMaxPlayers;
    private boolean practiceEnabled;

    private KitEditorManager kitEditorManager;

    public PracticeManager() {
        Tasks.runLater(this::init, 20*3);
    }

    public void init(){
        this.gameData = UniversalUHC.get().getGameManager().getGameData();
        this.kitEditorManager = new KitEditorManager();
        this.practice = new Practice();

        if (gameData.isPracticeEnabled()) {
            practice.enable();
        } else {
            practice.disable();
        }
    }

    private int totalPracticePlayers() {
        return practicePlayers.size();
    }

    public void onJoin(Player player){
        if(totalPracticePlayers() >= practiceMaxPlayers) {
            player.sendMessage(CC.translate("&cThe practice uhc is full!"));
            return;
        }

        this.practicePlayers.add(player);
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();
        UHCStats stats = UHCStats.getStats(player.getUniqueId()).join();

        profile.setPractice(true);
        stats.setPracticeDeaths(0);
        stats.setPracticeKills(0);
        stats.setPracticeKillStreak(0);
        stats.setPracticeMaxKillStreak(0);


    }

    public void leavePractice(Player player) {
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();
        UHCStats stats = UHCStats.getStats(player.getUniqueId()).join();

        profile.setPractice(false);
        stats.setPracticeKillStreak(0);
        stats.setPracticeMaxKillStreak(0);
        stats.setPracticeDeaths(stats.getPracticeDeaths() + 1);
        stats.setPracticeKills(0);

        practicePlayers.remove(player);
        UniversalUHC.get().getPlayerManager().teleportToSpawn(player);
        player.sendMessage(CC.translate("&7You have left the practice uhc!"));
    }

    public void leaveAllPracticePlayers() {
        practicePlayers.forEach(this::leavePractice);
    }
}
