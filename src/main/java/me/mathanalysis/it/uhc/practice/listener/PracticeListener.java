package me.mathanalysis.it.uhc.practice.listener;

import com.google.common.collect.Lists;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import me.mathanalysis.it.uhc.utils.Tasks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class PracticeListener implements Listener {

    private final UniversalUHC plugin = UniversalUHC.get();
    private final List<Block> blocks = Lists.newArrayList();

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event){
        if (event.getEntity().getKiller() == null || event.getEntity() == null){
            return;
        }

        Player killer = event.getEntity().getKiller();
        Player dead = event.getEntity();

        UHCStats killerStats = UHCStats.getStats(killer.getUniqueId()).join();
        UHCStats deadStats = UHCStats.getStats(dead.getUniqueId()).join();

        event.setDeathMessage(null);

        if (plugin.getPracticeManager().isPracticeEnabled()){
            killerStats.setKills(killerStats.getKills() + 1);
            killerStats.setPracticeKillStreak(killerStats.getPracticeKillStreak() + 1);
            killerStats.setPracticeMaxKillStreak(Math.max(killerStats.getPracticeMaxKillStreak(), killerStats.getPracticeKillStreak()));

            deadStats.setKills(0);
            deadStats.setPracticeMaxKillStreak(0);
            deadStats.setPracticeKillStreak(0);
            deadStats.setDeaths(deadStats.getDeaths() + 1);

            plugin.getPracticeManager().getPracticePlayers().stream().filter(player -> UHCProfile.getProfile(player.getUniqueId()).join().isPractice())
                    .forEach(player -> player.sendMessage(killer.getName() + " &7ha ucciso &c" + dead.getName() + "&7!"));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();

        if (plugin.getPracticeManager().isPracticeEnabled() || profile.isPractice()) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase(plugin.getWorldFile().getString("Worlds.uhc_practice.name"))) {
                blocks.add(event.getBlock());
            }
        }
    }

    @EventHandler
    public void onPracticeBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UHCProfile profile = UHCProfile.getProfile(player.getUniqueId()).join();

        if (plugin.getPracticeManager().isPracticeEnabled() && profile.isPractice()) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase(plugin.getWorldFile().getString("Worlds.uhc_practice.name"))) {
                Tasks.timerAsync(() -> blocks.forEach(block -> block.getLocation().getBlock().setType(Material.AIR)), 0L, 100L);
            }
        }
    }
}
