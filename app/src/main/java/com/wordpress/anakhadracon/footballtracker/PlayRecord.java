package com.wordpress.anakhadracon.footballtracker;

import java.util.ArrayList;

/**
 * Created by c16613 on 10/20/2015.
 */
public class PlayRecord
{
    Integer mPlayNumber;
    EPlayType mPlayType;
    ArrayList<EFouls> mFouls;

    public PlayRecord(Integer play, EPlayType type, ArrayList<EFouls> fouls)
    {
        mPlayNumber = play;
        mPlayType = type;
        mFouls = fouls;
    }

}
