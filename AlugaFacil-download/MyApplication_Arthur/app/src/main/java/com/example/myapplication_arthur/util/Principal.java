package com.example.myapplication_arthur.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Principal {

    private static HashMap<String,Long> chaves;
    private static Principal instance;

    private Principal(){
        chaves = new HashMap<>();
    }

    public static Principal getInstance(){
        if(instance == null){
            instance = new Principal();
        }

        return instance;
    }

    public static Principal getInstance(boolean clear){
        getInstance();

        if(clear){
            chaves.clear();
        }

        return instance;
    }

    public static HashMap<String, Long> getChaves() {
        return chaves;
    }

    public static void setChaves(HashMap<String, Long> chaves) {
        Principal.chaves = chaves;
    }
}
