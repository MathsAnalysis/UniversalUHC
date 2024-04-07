package me.mathanalysis.it.uhc.command;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.CC;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command("uhcadmin")
@CommandPermission("uhc.command.admin")
public class UHCCommand {

    private final UniversalUHC core = UniversalUHC.get();

    @Subcommand("host")
    public void onHostUHC(Player player){
        UniversalUHC.get().getPlayerManager().enableSpectator(player);
        player.sendMessage(CC.translate("&aYou are now hosting a UHC!"));
    }


}
