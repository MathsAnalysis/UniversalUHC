package me.mathanalysis.it.uhc.manager;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.Profile;
import me.mathanalysis.it.uhc.utils.CC;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlayerManager {

    private List<UUID> spectators;
    private List<UUID> uhcHosts;
    private List<UUID> uhcMods;

    public PlayerManager(){
        this.spectators = Lists.newArrayList();
        this.uhcHosts = Lists.newArrayList();
        this.uhcMods = Lists.newArrayList();
    }

    public void enableSpectator(Player player){
        Profile profile = Profile.getProfile(player.getUniqueId());

        profile.setSpectator(true);


        if(profile.isSpectator()){
            spectators.add(player.getUniqueId());

            if (profile.isUhcHost()) {
                uhcHosts.add(player.getUniqueId());
            }

            if (profile.isUhcMod()) {
                uhcMods.add(player.getUniqueId());
            }
        }

        UniversalUHC.get().getPlayerManager().getSpectators().add(player.getUniqueId());

        if (profile.isUhcHost()){
            UniversalUHC.get().getPlayerManager().getUhcHosts().add(player.getUniqueId());
            player.sendMessage(CC.translate("&7You are now hosting the UHC!"));
        }

        if (profile.isUhcMod()){
            UniversalUHC.get().getPlayerManager().getUhcMods().add(player.getUniqueId());
            player.sendMessage(CC.translate("&7You are now moderating the UHC!"));
        }


        player.sendMessage(CC.translate("&aYou are now a spectator!"));

    }

}
