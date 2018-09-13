package com.mygdx.test;

public class Utils {

    //private
    private static Utils instance;

    //singleton
    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    //public
    public static final boolean isDebugging = false;
    public static int totalSleep = 60000;

    //strings
    public static final String DMenuOption1 = "Model";
    public static final String DMenuOption2 = "Tutorial";
    public static final String DMenuOption3 = "Exit";

    public static final String DSkinPath = "skins/comic-ui.json";


}
