package me.mathanalysis.it.uhc.command.manager;

import me.mathanalysis.it.uhc.UniversalUHC;
import me.mathanalysis.it.uhc.UniversalUHCPlugin;
import me.mathanalysis.it.uhc.command.*;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public class UHCCommandManager {

    private final BukkitCommandHandler handler;

    public UHCCommandManager(){
        this.handler = BukkitCommandHandler.create(UniversalUHC.get().getPlugin());
        this.handler.registerDependency(UniversalUHCPlugin.class, UniversalUHC.get().getPlugin());
        handler.setExceptionHandler(new UHCCommandException());

        loadCommand();
    }

    private void loadCommand(){
        handler.register(
                new DaysDifferenceCommand(),
                new UHCCommand(),
                new SetSpawnCommand(),
                new PracticeAdminCommand(),
                new FirstJoinCommand()
        );
    }
}
