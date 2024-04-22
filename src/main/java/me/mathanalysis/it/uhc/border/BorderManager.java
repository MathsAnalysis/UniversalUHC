package me.mathanalysis.it.uhc.border;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.game.GameData;
import me.mathanalysis.it.uhc.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class BorderManager {

    private final UniversalUHC plugin = UniversalUHC.get();
    private Object2ObjectOpenHashMap<UUID, Long> lastCheck = new Object2ObjectOpenHashMap<>();

    public void checkLocation(Player player, Location to, Location from) {
        GameData gameData = plugin.getGameManager().getGameData();

        int size = gameData.getBorder();
        Location newLocation = player.getLocation().clone();
        if(!LocationUtil.checkZone(newLocation, size)){
            newLocation.setX(Math.min(newLocation.getX(), size - 5));
            newLocation.setZ(Math.min(newLocation.getZ(), size - 5));
            LocationUtil.safeTeleport(player, newLocation);

            if (lastChecked(player)) {
                Msg.playEffect(player, true);
                lastCheck.put(player.getUniqueId(), System.currentTimeMillis());
                player.sendMessage(CC.translate(CC.PREFIX + "&cYou have reached the border!"));
            }
        }
    }

    public void checkShrinkLocation(){
        boolean team = plugin.getGameManager().getGameData().isTeam();
        Tasks.runTask(()->{
            int border = plugin.getGameManager().getGameData().getBorder();
            World world = Bukkit.getWorld(plugin.getWorldManager().getUhcWorld().getName());

            //todo CombatLog the border shrink

            if (team){

            }else{
                sendLocation(border);
            }


        });
    }


    private void sendLocation(int border){
        Bukkit.getOnlinePlayers().stream().filter(online -> {
            Location location = online.getLocation();
            World playerWorld = location.getWorld();
            return Math.abs(location.getBlockX()) > border || Math.abs(location.getBlockZ()) > border || playerWorld.getName().equalsIgnoreCase(plugin.getWorldFile().getString("world.uhc_nether.name"));
        }).forEach(online -> plugin.getWorldManager().getGenerateForPlayer().thenAccept(location ->{
            int blockX = location.getBlockX();
            int blockZ = location.getBlockZ();
            online.setNoDamageTicks(60);
            online.setFallDistance(0.0f);
            for(int blockY = 2; blockY < 5; blockY++) {
                Location blockLocation = new Location(location.getWorld(), blockX, blockY, blockZ);
                blockLocation.getBlock().setType(Material.AIR);
            }
            LocationUtil.safeTeleport(online, LocationUtil.getHighest(location));
            Msg.playEffect(online, true);
        }));
    }





    public boolean lastChecked(Player player) {
        return !lastCheck.containsKey(player.getUniqueId()) || System.currentTimeMillis() - lastCheck.get(player.getUniqueId()) > TimeUnit.SECONDS.toMillis(1L);
    }

}
