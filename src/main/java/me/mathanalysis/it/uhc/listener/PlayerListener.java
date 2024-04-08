package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        LocalDateTime localDateTime = LocalDateTime.now();

        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId());
        profile.setFirstJoin(localDateTime);
        profile.setLastJoin(localDateTime.format(Utility.dateFormatter()));
        profile.save();


        List<String> messages = UniversalUHC.get().getMessageFile().getStringList("Join");
        messages.forEach(message -> UniversalUHC.get().getAudiences().sender(player).sendMessage(CC.deserialize(message)));
    }
}
