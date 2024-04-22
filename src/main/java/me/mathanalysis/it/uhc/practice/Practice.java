package me.mathanalysis.it.uhc.practice;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.practice.listener.PracticeListener;
import me.mathanalysis.it.uhc.practice.structure.PracticeInterface;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.Msg;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class Practice implements PracticeInterface {

    public UniversalUHC plugin = UniversalUHC.get();

    @Override
    public void enable() {
        plugin.getPracticeManager().setPracticePlayers(new ArrayList<>());
        plugin.getPracticeManager().setPracticeEnabled(plugin.getGameManager().getGameData().isPracticeEnabled());
        plugin.getPracticeManager().setPracticeMaxPlayers(plugin.getGameManager().getGameData().getPracticeMaxPlayers());
        Bukkit.getPluginManager().registerEvents(new PracticeListener(), UniversalUHC.get().getPlugin());
        Msg.broadcastMessage(CC.PREFIX + "&aIl practice uhc è stato abilitato!");
    }

    @Override
    public void disable() {
        plugin.getPracticeManager().setPracticeEnabled(plugin.getGameManager().getGameData().isPracticeEnabled());
        plugin.getPracticeManager().setPracticeMaxPlayers(plugin.getGameManager().getGameData().getPracticeMaxPlayers());
        HandlerList.unregisterAll(new PracticeListener());
        plugin.getPracticeManager().leaveAllPracticePlayers();
        Msg.broadcastMessage(CC.PREFIX + "&cIl practice uhc è stato disabilitato!");
    }
}
