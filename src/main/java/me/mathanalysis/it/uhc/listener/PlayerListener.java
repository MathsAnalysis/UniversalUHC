package me.mathanalysis.it.uhc.listener;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.profile.UHCStats;
import me.mathanalysis.it.uhc.ranking.EloRank;
import me.mathanalysis.it.uhc.utils.CC;
import me.mathanalysis.it.uhc.utils.Tasks;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        LocalDateTime localDateTime = LocalDateTime.now();

        UHCProfile.getProfile(player.getUniqueId()).thenAccept(profile -> {
            profile.setFirstJoin(localDateTime);
            profile.save();
        }).join();


        List<String> messages = UniversalUHC.get().getMessageFile().getStringList("Join");
        messages.forEach(message -> UniversalUHC.get().getAudiences().sender(player).sendMessage(CC.deserialize(message)));

        Tasks.runLater(()-> UniversalUHC.get().getPlayerManager().teleportToSpawn(player), 3L);

    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event){
        UHCStats stats = UHCStats.getStats(event.getPlayer().getUniqueId()).join();

        ChatColor chatColor = EloRank.getRank(stats.getElo()).getChatColor();
        String nameRank = EloRank.getRank(stats.getElo()).getName();

        event.setFormat(CC.translate("&7[&f" + chatColor + nameRank+ "&7] &f" + event.getPlayer().getName() + "&7: &f" + event.getMessage()));
    }
}
