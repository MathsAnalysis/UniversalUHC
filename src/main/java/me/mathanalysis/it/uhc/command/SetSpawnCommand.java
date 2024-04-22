package me.mathanalysis.it.uhc.command;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.LocationUtil;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class SetSpawnCommand {

    @Command("setspawn")
    @CommandPermission("uhc.command.setspawn")
    public void onSetSpawn(Player player){
        UniversalUHC.get().getConfig().set("Spawn", LocationUtil.serializeLocation(player.getLocation()));
        UniversalUHC.get().getConfig().save(true);
        player.sendMessage(CC.translate(CC.PREFIX + "&aHai impostato il punto di spawn!"));
    }
}
