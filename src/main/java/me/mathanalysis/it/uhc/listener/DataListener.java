package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        new UHCProfile(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        new UHCStats(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId());
        profile.setLastJoin(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        profile.save();

        UHCStats.getStats().remove(event.getPlayer().getUniqueId());
        UHCProfile.getProfiles().remove(event.getPlayer().getUniqueId());
    }
}
