package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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

    public String serialize(Component component) {
        return MiniMessage.miniMessage().serialize(component);
    }

    public Component deserialize(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }

    public List<String> serialize(List<Component> components) {
        List<String> serialized = new ArrayList<>();
        components.forEach(component -> serialized.add(serialize(component)));
        return serialized;
    }

    public List<Component> deserialize(List<String> inputs) {
        List<Component> deserialized = new ArrayList<>();
        inputs.forEach(input -> deserialized.add(deserialize(input)));
        return deserialized;
    }
}
