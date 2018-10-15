package com.company;

import java.util.ArrayList;

public class PlayList extends Element{
    private ArrayList<Track> tracks = new ArrayList<>();

    PlayList(String name) {
        super(name);
    }

    @Override
    String getInfo() {
        return getName();
    }

    @Override
    protected void addElement(Element element) {}

    public void addTrack(Track t) {
        if(!tracks.contains(t))
            tracks.add(t);
    }

    public void addTrack(ArrayList<Track> tracks){
        for (Track track : tracks)
            addTrack(track);
    }

    public void removeTrack(Track t) {
        tracks.remove(t);
    }

    public ArrayList<Track> getTracks(){
        return tracks;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + name;
    }
}
