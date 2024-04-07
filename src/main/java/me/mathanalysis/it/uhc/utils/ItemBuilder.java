package me.mathanalysis.it.uhc.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    public final ItemStack item;

    public ItemBuilder(Material material){
        this.item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
    }

    public ItemBuilder setName(String name){
        ItemMeta  meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(name));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAmount(int amount){
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setDurability(short durability){
        ItemMeta meta = item.getItemMeta();
        item.setDurability(durability);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable){
        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(unbreakable);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(CC.translate(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addLoreLine(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add(CC.translate(line));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }


    public ItemStack get(){
        return item;
    }
}
