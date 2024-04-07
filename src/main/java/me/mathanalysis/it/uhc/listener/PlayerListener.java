package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<String> messages = UniversalUHC.get().getMessageFile().getStringList("Join");
        messages.forEach(message -> UniversalUHC.get().getAudiences().sender(player).sendMessage(CC.deserialize(message)));
    }
}
