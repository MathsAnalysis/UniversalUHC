package me.mathanalysis.it.uhc.command;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.game.GameData;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.Msg;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.*;
import revxrsal.commands.bukkit.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("practiceadmin")
@CommandPermission("uhc.command.practiceadmin")
public class PracticeAdminCommand {

    private final UniversalUHC plugin = UniversalUHC.get();

    @Subcommand("enable")
    @Description("Enable the practice uhc")
    public void onEnablePractice(BukkitCommandActor actor){
        GameData gameData = UniversalUHC.get().getGameManager().getGameData();

        String name = (actor instanceof Player) ? ((Player) actor).getName() : "&c&lConsole";

        gameData.setPracticeEnabled(true);
        Msg.broadcastMessage(CC.translate(CC.PREFIX + "&7Practice has been enabled by &d" + name));
    }

    @Subcommand("disable")
    @Description("Disable the practice uhc")
    public void onDisablePractice(BukkitCommandActor actor){
        GameData gameData = UniversalUHC.get().getGameManager().getGameData();

        String name = (actor instanceof Player) ? ((Player) actor).getName() : "&c&lConsole";

        gameData.setPracticeEnabled(false);
        Msg.broadcastMessage(CC.translate(CC.PREFIX + "&cPractice e' ora disabilitato nelle uhc da &d" + name));
    }

    @Subcommand("setmaxplayers")
    @Usage("/practiceadmin setmaxplayers <maxslot>")
    @Description("Imposta il numero massimo di giocatori per il practice")
    public void onSetMaxPlayers(Player player, @Named("maxslot") int maxPlayers){
        GameData gameData = UniversalUHC.get().getGameManager().getGameData();

        gameData.setPracticeMaxPlayers(maxPlayers);
        Msg.broadcastMessage(CC.translate(CC.PREFIX + "&7Il giocatore massimo e' stato impostato ad &d" + maxPlayers + " &7da &d" + player.getName()));
        Msg.broadcastMessage(CC.translate(CC.PREFIX + "&7Ricarica il server per applicare le modifiche"));
    }

    @Subcommand("totalplayers")
    @Description("Get the total players in the practice uhc")
    public void onTotalPlayers(Player player){
        player.sendMessage(CC.translate(CC.PREFIX + "&7Giocatori totali nel practice uhc &d" + plugin.getPracticeManager().getPracticePlayers().size()));
    }

}
