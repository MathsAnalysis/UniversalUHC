package me.mathanalysis.it.uhc.command;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
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
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();

        if (profile.isUhcHost()){
            profile.setUhcHost(false);
            UniversalUHC.get().getPlayerManager().disableSpectator(player);
        }else {
            profile.setUhcHost(true);
            UniversalUHC.get().getPlayerManager().enableSpectator(player);
        }
    }

    @Subcommand("mod")
    public void onModeratingUHC(Player player){
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();

        if (!profile.isUhcMod()){
            profile.setUhcMod(true);
            UniversalUHC.get().getPlayerManager().disableSpectator(player);
        }else {
            profile.setUhcMod(false);
            UniversalUHC.get().getPlayerManager().enableSpectator(player);
        }
    }


}
