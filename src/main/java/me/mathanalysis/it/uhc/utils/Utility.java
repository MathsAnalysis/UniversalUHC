package me.mathanalysis.it.uhc.utils;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

@UtilityClass
public class Utility {

    public DateTimeFormatter dateFormatter(){
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
}
