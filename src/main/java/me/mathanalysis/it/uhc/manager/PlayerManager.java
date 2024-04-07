package me.mathanalysis.it.uhc.manager;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlayerManager {

    private List<UUID> spectators;
    private List<UUID> uhcHosts;
    private List<UUID> uhcMods;

    public void enableSpectator(){

    }

}
