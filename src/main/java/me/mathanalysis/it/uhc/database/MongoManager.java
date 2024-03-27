package me.mathanalysis.it.uhc.database;

import lombok.Data;

@Data
public class MongoManager {

    private boolean connected = false;
    public static MongoManager instance;

    public MongoManager(String URI, String database){


    }



    public MongoManager getInstance(){
        if(instance == null && !connected){
            instance = new MongoManager();
        }
        return instance;
    }
}
