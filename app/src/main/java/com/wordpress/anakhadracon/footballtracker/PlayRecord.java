package com.wordpress.anakhadracon.footballtracker;

import java.util.ArrayList;

/**
 * Created by c16613 on 10/20/2015.
 */
public class PlayRecord
{
    Integer mPlayNumber;
    String mPlayType;
    ArrayList<FoulRecord> mFouls;

    public PlayRecord(Integer play, String type, ArrayList<FoulRecord> fouls)
    {
        mPlayNumber = play;
        mPlayType = type;
        mFouls = fouls;
    }

}
