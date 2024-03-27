package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;
import me.mathanalysis.it.uhc.UniversalUHC;
import org.bukkit.Bukkit;
import org.bukkit.Utility;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@UtilityClass
public class Tasks {

    public BukkitTask runLater(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLater(UniversalUHC.get().getPlugin(), runnable, delay);
    }

    public BukkitTask runTimer(Runnable runnable, long delay, long period) {
        return Bukkit.getScheduler().runTaskTimer(UniversalUHC.get().getPlugin(), runnable, delay, period);
    }

    public BukkitTask runTask(Runnable runnable) {
        return Bukkit.getScheduler().runTask(UniversalUHC.get().getPlugin(), runnable);
    }


    public BukkitTask laterAsync(Runnable runnable, long delay) {
        return Bukkit.getScheduler().runTaskLaterAsynchronously(UniversalUHC.get().getPlugin(), runnable, delay);
    }


    public BukkitTask timerAsync(Runnable runnable, long delay, long period) {
        return Bukkit.getScheduler().runTaskTimerAsynchronously(UniversalUHC.get().getPlugin(), runnable, delay, period);
    }


    public BukkitTask asyncTask(Runnable runnable) {
        return Bukkit.getScheduler().runTaskAsynchronously(UniversalUHC.get().getPlugin(), runnable);
    }

    public void cancelTask(BukkitTask task) {
        task.cancel();
    }

    public void cancelTasks() {
        Bukkit.getScheduler().cancelTasks(UniversalUHC.get().getPlugin());
    }


}
