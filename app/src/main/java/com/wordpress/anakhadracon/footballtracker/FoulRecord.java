package com.wordpress.anakhadracon.footballtracker;

/**
 * Created by c16613 on 10/22/2015.
 */
public class FoulRecord {
    String callingOfficial;
    String foul;

    public FoulRecord()
    {
        callingOfficial = "";
        foul = "None";
    }

    public FoulRecord(String official, String inFoul)
    {
        callingOfficial = official;
        foul = inFoul;
    }
}
