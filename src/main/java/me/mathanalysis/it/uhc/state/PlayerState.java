package me.mathanalysis.it.uhc.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PlayerState {

    PLAYING("playing"),
    LOBBY("lobby"),
    SPECTATING("spectating");

    private final String name;

    public static PlayerState getState(String name){
        return Stream.of(values()).filter(state -> state.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
