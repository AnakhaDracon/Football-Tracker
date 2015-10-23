package com.wordpress.anakhadracon.footballtracker;

/**
 * Created by c16613 on 10/22/2015.
 */
public class FoulRecord {
    String _callingOfficial;
    String _foul;
    String _team;

    public FoulRecord()
    {
        _callingOfficial = "";
        _foul = "None";
        _team = "None";
    }

    public FoulRecord(String official, String inFoul, String team)
    {
        _callingOfficial = official;
        _foul = inFoul;
        _team = team;
    }

    @Override
    public String toString()
    {
        return _team + "," + _foul + "," + _callingOfficial;
    }
}
