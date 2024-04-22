package me.mathanalysis.it.uhc.command;

import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.utils.CC;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

public class DaysDifferenceCommand {

    @Command("daysdifference")
    @CommandPermission("uhc.command.daysdifference")
    public void onDaysDifference(Player player) {
        UHCProfile.getProfile(player.getUniqueId()).thenAccept(profile -> {
            if (profile == null) {
                player.sendMessage(CC.translate(CC.PREFIX + "&cProfilo non trovato."));
                return;
            }
        }).exceptionally(throwable -> {
            player.sendMessage(CC.translate(CC.PREFIX + "&c&lERRORE &7Contatta lo staff per ulteriori informazioni."));
            return null;
        });
    }
}
