package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@UtilityClass
public class Msg {

    public void sendMessage(String input){
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(CC.translate(input)));
    }

    public void sendMessage(String input, String permission){
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(player.hasPermission(permission)){
                player.sendMessage(CC.translate(input));
            }
        });
    }

    public void broadcastMessage(String input){
        Bukkit.broadcastMessage(CC.translate(input));
    }

    public void playEffect(Player player, boolean msg) {
        player.getWorld().playEffect(player.getLocation(), Effect.LARGE_SMOKE,2, 2);
        player.playSound(player.getLocation(), Sound.EXPLODE, 1.0f, 2.0f);

        if(msg) {
            player.sendMessage(CC.translate(CC.PREFIX + "&cYou were shrunk in the border."));
        }
    }
}
