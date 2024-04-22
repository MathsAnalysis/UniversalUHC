package me.mathanalysis.it.uhc.command.manager;

import me.mathanalysis.it.uhc.utils.CC;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.bukkit.exception.*;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.exception.*;

import java.util.concurrent.TimeUnit;

public class UHCCommandException extends BukkitExceptionAdapter {

    @Override
    public void senderNotPlayer(@NotNull CommandActor actor, @NotNull SenderNotPlayerException exception) {
        actor.reply(CC.translate("&cOnly players can execute this command!"));
    }

    @Override
    public void senderNotConsole(@NotNull CommandActor actor, @NotNull SenderNotConsoleException exception) {
        actor.reply(CC.translate("&cOnly console can execute this command!"));
    }

    @Override
    public void invalidPlayer(@NotNull CommandActor actor, @NotNull InvalidPlayerException exception) {
        actor.reply(CC.translate("&cPlayer not found!"));
    }

    @Override
    public void invalidWorld(@NotNull CommandActor actor, @NotNull InvalidWorldException exception) {
        actor.reply(CC.translate("&cWorld not found!"));
    }

    @Override
    public void malformedEntitySelector(@NotNull CommandActor actor, @NotNull MalformedEntitySelectorException exception) {
        actor.reply(CC.translate("&cInvalid entity selector!"));
    }

    @Override
    public void moreThanOnePlayer(@NotNull CommandActor actor, @NotNull MoreThanOnePlayerException exception) {
        actor.reply(CC.translate("&cMore than one player found!"));
    }

    @Override
    public void nonPlayerEntities(@NotNull CommandActor actor, @NotNull NonPlayerEntitiesException exception) {
        actor.reply(CC.translate("&cNon-player entities are not supported!"));
    }

    @Override
    public void onUnhandledException(@NotNull CommandActor actor, @NotNull Throwable throwable) {
        actor.reply(CC.translate("&cAn error occurred while executing the command!"));
    }

    @Override
    public void numberNotInRange(@NotNull CommandActor actor, @NotNull NumberNotInRangeException exception) {
        actor.reply(CC.translate("&cNumber not in range!"));
    }

    @Override
    public void sendableException(@NotNull CommandActor actor, @NotNull SendableException exception) {
        actor.reply(CC.translate("&c" + exception.getMessage()));
    }

    @Override
    public void invalidHelpPage(@NotNull CommandActor actor, @NotNull InvalidHelpPageException exception) {
        actor.reply(CC.translate("&cInvalid help page!"));
    }

    @Override
    public void cooldown(@NotNull CommandActor actor, @NotNull CooldownException exception) {
        actor.reply(CC.translate("&cYou can use this command in &c&l" + exception.getTimeLeft(TimeUnit.SECONDS) + " &cseconds!"));
    }

    @Override
    public void noSubcommandSpecified(@NotNull CommandActor actor, @NotNull NoSubcommandSpecifiedException exception) {
        actor.reply(CC.translate("&cNo subcommand specified!"));
    }

    @Override
    public void invalidSubcommand(@NotNull CommandActor actor, @NotNull InvalidSubcommandException exception) {
        actor.reply(CC.translate("&cInvalid subcommand!"));
    }

    @Override
    public void invalidCommand(@NotNull CommandActor actor, @NotNull InvalidCommandException exception) {
        actor.reply(CC.translate("&cInvalid command!"));
    }

    @Override
    public void tooManyArguments(@NotNull CommandActor actor, @NotNull TooManyArgumentsException exception) {
        actor.reply(CC.translate("&cToo many arguments!"));
    }

    @Override
    public void commandInvocation(@NotNull CommandActor actor, @NotNull CommandInvocationException exception) {
        actor.reply(CC.translate("&cAn error occurred while executing the command!"));
    }

    @Override
    public void argumentParse(@NotNull CommandActor actor, @NotNull ArgumentParseException exception) {
        super.argumentParse(actor, exception);
    }

    @Override
    public void noPermission(@NotNull CommandActor actor, @NotNull NoPermissionException exception) {
        actor.reply(CC.translate("&cYou do not have permission to execute this command!"));
    }

    @Override
    public void invalidBoolean(@NotNull CommandActor actor, @NotNull InvalidBooleanException exception) {
        actor.reply(CC.translate("&cInvalid boolean!"));
    }

    @Override
    public void invalidURL(@NotNull CommandActor actor, @NotNull InvalidURLException exception) {
        actor.reply(CC.translate("&cInvalid URL!"));
    }

    @Override
    public void invalidUUID(@NotNull CommandActor actor, @NotNull InvalidUUIDException exception) {
        actor.reply(CC.translate("&cInvalid UUID!"));
    }

    @Override
    public void invalidNumber(@NotNull CommandActor actor, @NotNull InvalidNumberException exception) {
        actor.reply(CC.translate("&cInvalid number!"));
    }

    @Override
    public void invalidEnumValue(@NotNull CommandActor actor, @NotNull EnumNotFoundException exception) {
        actor.reply(CC.translate("&cInvalid enum value!"));
    }

    @Override
    public void missingArgument(@NotNull CommandActor actor, @NotNull MissingArgumentException exception) {
        actor.reply(CC.translate("&cMissing argument!"));
    }
}
