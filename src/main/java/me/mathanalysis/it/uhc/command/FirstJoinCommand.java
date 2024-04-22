package me.mathanalysis.it.uhc.command;

import com.mongodb.client.model.Filters;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.profile.UHCProfile;
import me.mathanalysis.it.uhc.utils.CC;
import org.bson.Document;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Named;
import revxrsal.commands.annotation.Optional;

public class FirstJoinCommand {

    @Command("firstjoin")
    public void onFirstJoin(Player player, @Default("me") @Optional @Named("target") String target) {
        UHCProfile.getProfile(player.getUniqueId());

        if (target.equals("me")) {
            player.sendMessage(CC.translate(CC.PREFIX + "&7Primo join: &d" + UHCProfile.getProfile(player.getUniqueId()).join().getFirstJoin()));
        } else {
            Document document = UniversalUHC.get().getMongoManager().getProfiles().find(Filters.eq("name", target)).first();

            if (document == null) {
                player.sendMessage(CC.translate(CC.PREFIX + "&cProfilo non trovato."));
                return;
            }

            player.sendMessage(CC.translate(CC.PREFIX + "&7Il primo join di &d" + target + " &7e' &d" + UHCProfile.getProfile(document.getString("name")).getFirstJoin()));
        }

    }
}
