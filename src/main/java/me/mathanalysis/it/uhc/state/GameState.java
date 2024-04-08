package me.mathanalysis.it.uhc.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum GameState {

    LOBBY("lobby", "&dLobby"),
    SCATTERING("scattering", "&3Scattering"),
    STARTING("starting", "&aStarting"),
    INGAME("ingame", "&cIngame"),
    ENDING("ending", "&eEnding"),;


    public final String name, displayName;

    public static GameState getName(String name) {
        return Stream.of(values()).filter(state -> state.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
