package com.github.JgamerXD.RandomUtilitiesMod;

/**
 * Created by Jan Kiko on 31.07.2014.
 */
public class Util {

    public static int getScaledProgress(int scale, int progress, int maxTime)
    {
        //System.out.println(progress+ " / " + maxTime + " = " + (float)progress / (float)maxTime);
        if (progress == 0)
            return 0;
        return (int)(scale * ((float)progress / (float)maxTime));
    }
}
