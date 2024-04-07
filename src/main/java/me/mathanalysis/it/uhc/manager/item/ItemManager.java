package me.mathanalysis.it.uhc.manager.item;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import me.mathanalysis.it.uhc.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public class ItemManager {

    public void giveItemStaff(Player player){
        ItemStack item = new ItemBuilder(Material.SKULL_ITEM).setName("&dCiao").get();
        player.getInventory().addItem(item);
    }
}
