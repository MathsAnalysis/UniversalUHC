package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Utility;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CC {

    public String translate(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public String translate(String input, Player player){
        return PlaceholderAPI.setPlaceholders(player, translate(input));
    }

    public List<String> translate(List<String> inputs){
        List<String> translated = new ArrayList<>();
        inputs.forEach(input -> translated.add(translate(input)));
        return translated;
    }

    public List<String> translate(List<String> inputs, Player player){
        List<String> translated = new ArrayList<>();
        inputs.forEach(input -> translated.add(translate(input, player)));
        return translated;
    }
}
