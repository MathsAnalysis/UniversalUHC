package me.mathanalysis.it.uhc.manager;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.LocationUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlayerManager {

    private UniversalUHC plugin = UniversalUHC.get();

    private List<UUID> spectators;
    private List<UUID> uhcHosts;
    private List<UUID> uhcMods;

    public PlayerManager(){
        this.spectators = Lists.newArrayList();
        this.uhcHosts = Lists.newArrayList();
        this.uhcMods = Lists.newArrayList();
    }

    public void enableSpectator(Player player){
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();


        if(!profile.isSpectator()){
            profile.setSpectator(true);

            spectators.add(player.getUniqueId());

            if (!profile.isUhcHost()) {
                uhcHosts.add(player.getUniqueId());
            }

            if (!profile.isUhcMod()) {
                uhcMods.add(player.getUniqueId());
            }
        }

        UniversalUHC.get().getPlayerManager().getSpectators().add(player.getUniqueId());

        if (profile.isUhcHost()){
            UniversalUHC.get().getPlayerManager().getUhcHosts().add(player.getUniqueId());
            player.sendMessage(CC.translate(CC.PREFIX + "&7Sei l'host della UHC!"));
        }

        if (profile.isUhcMod()){
            UniversalUHC.get().getPlayerManager().getUhcMods().add(player.getUniqueId());
            player.sendMessage(CC.translate(CC.PREFIX + "&7Sei ora un moderatore della UHC!"));
        }


        player.sendMessage(CC.translate(CC.PREFIX + "&7Sei uno spettatore della UHC!"));
    }


    public void disableSpectator(Player player){
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();


        if(profile.isSpectator()){
            profile.setSpectator(false);
            spectators.remove(player.getUniqueId());

            if (profile.isUhcHost()) {
                uhcHosts.remove(player.getUniqueId());
                player.sendMessage(CC.translate(CC.PREFIX + "&cNon sei piu' l'host della UHC!"));
            }

            if (profile.isUhcMod()) {
                uhcMods.remove(player.getUniqueId());
                player.sendMessage(CC.translate(CC.PREFIX + "&cNon sei piu' un moderatore della UHC!"));
            }
        }

        UniversalUHC.get().getPlayerManager().getSpectators().remove(player.getUniqueId());
        player.sendMessage(CC.translate(CC.PREFIX + "&cNon sei lo spettatore della UHC!"));
    }

    public void teleportToSpawn(Player player){
        FileConfiguration config = UniversalUHC.get().getConfig().getConfig();

        if (config == null) {
            player.sendMessage(CC.translate("&cIl config non è stato caricato correttamente!"));
            return;
        }

        if (config.getString("Spawn") == null || config.getString("Spawn").isEmpty()){
            player.sendMessage(CC.translate(CC.PREFIX + "&cIl punto di spawn non e' stato impostato!"));
            player.sendMessage(CC.translate(CC.PREFIX + "&cContatta un amministratore per impostare il punto di spawn! &7(/setspawn)"));
            return;
        }

        player.teleport(LocationUtil.deserializeLocation(config.getString("Spawn")));
    }

}
