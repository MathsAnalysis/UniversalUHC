package me.mathanalysis.it.uhc.utils;

import me.mathanalysis.it.uhc.UniversalUHC;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigFile extends YamlConfiguration {

    public final String finalName;

    public ConfigFile(Plugin plugin, String name, boolean load, boolean forceCreate) {
        this.finalName = name;

        if(forceCreate){
            this.create();
        }else {
            try {
                File file = new File(plugin.getDataFolder(), name + ".yml");

                if (!file.exists()) {
                    plugin.saveResource(name + ".yml", false);
                }

                if (load) {
                    this.load(file);
                }
            }catch (Exception e) {
                e.printStackTrace();
                UniversalUHC.get().getPlugin().getLogger().severe("Error loading config file " + name + ".yml");
            }
        }
    }

    public void create(){
        try {
            File file = new File(UniversalUHC.get().getPlugin().getDataFolder(), this.finalName + ".yml");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UniversalUHC.get().getPlugin().getLogger().severe("Error creating config file " + this.finalName + ".yml");
        }
    }


    public void load(){
        try {
            this.load(new File(UniversalUHC.get().getPlugin().getDataFolder(), this.finalName + ".yml"));
        } catch (Exception e) {
            e.printStackTrace();
            UniversalUHC.get().getPlugin().getLogger().severe("Error loading config file " + this.finalName + ".yml");
        }
    }


    public void save(){
        try {
            this.save(new File(UniversalUHC.get().getPlugin().getDataFolder(), this.finalName + ".yml"));
        } catch (Exception e) {
            e.printStackTrace();
            UniversalUHC.get().getPlugin().getLogger().severe("Error saving config file " + this.finalName + ".yml");
        }
    }

    public void reload(){
        try {
            this.load(new File(UniversalUHC.get().getPlugin().getDataFolder(), this.finalName + ".yml"));
        } catch (Exception e) {
            e.printStackTrace();
            UniversalUHC.get().getPlugin().getLogger().severe("Error reloading config file " + this.finalName + ".yml");
        }
    }

    public void setDefaults(String path, Object value){
        if (!this.contains(path)) {
            this.set(path, value);
        }
    }


    public void deleteFile(){
        try {
            File file = new File(UniversalUHC.get().getPlugin().getDataFolder(), this.finalName + ".yml");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            UniversalUHC.get().getPlugin().getLogger().severe("Error deleting config file " + this.finalName + ".yml");
        }
    }

}
