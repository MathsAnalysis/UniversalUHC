package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import me.mathanalysis.it.uhc.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        new UHCProfile(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        new UHCStats(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        UHCProfile.getProfile(player.getUniqueId()).thenAccept(profile -> {
            profile.setLastJoin(Utility.getDateFormatter());
            profile.save();
        }).join();

        UHCStats.getStats().remove(event.getPlayer().getUniqueId());
        UHCProfile.getProfiles().remove(event.getPlayer().getUniqueId());
    }
}
