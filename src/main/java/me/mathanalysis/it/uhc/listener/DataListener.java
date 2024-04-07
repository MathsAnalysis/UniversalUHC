package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.profile.Profile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DataListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        new Profile(event.getPlayer().getUniqueId(), event.getPlayer().getName());
        new UHCStats(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event){
        UHCStats.getStats().remove(event.getPlayer().getUniqueId());
        Profile.getProfiles().remove(event.getPlayer().getUniqueId());
    }
}
