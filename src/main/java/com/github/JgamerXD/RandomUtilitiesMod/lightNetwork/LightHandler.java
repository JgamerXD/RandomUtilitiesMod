package com.github.JgamerXD.RandomUtilitiesMod.lightNetwork;

import java.util.*;

/**
 * Created by JgamerXD on 05.08.2014.
 */
public class LightHandler {

    double lightLevel = 0;
    Set<LightSource> sources = new HashSet<LightSource>();


    public void addSource(LightSource source)
    {
        sources.add(source);
    }

    public void removeSource(LightSource source)
    {
        sources.remove(source);
    }

    public void updateSources()
    {
        lightLevel = 0;
        for(LightSource s : sources)
        {
            if(s.valid())
                lightLevel += s.strength;
            else
                removeSource(s);
        }
    }

    public Iterator<LightSource> getSources()
    {
        return sources.iterator();
    }


}
