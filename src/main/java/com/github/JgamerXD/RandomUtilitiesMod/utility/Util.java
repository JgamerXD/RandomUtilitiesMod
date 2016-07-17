package com.github.JgamerXD.RandomUtilitiesMod.utility;

/**
 * Created by JgamerXD on 31.07.2014.
 */
public class Util {

    public static int getScaledAmount(int scale, double part, double maxValue)
    {
        //System.out.println(part+ " / " + maxValue + " = " + (float)part / (float)maxValue);
        if (part == 0 || maxValue == 0)
            return 0;
        return (int)(scale * (part / maxValue));
    }
}
