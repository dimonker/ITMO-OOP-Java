package com.company;

import java.util.ArrayList;

public class Artist extends Element {
    ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();

    public Artist(String name) {
        super(name);
    }

    public void addTrack(Track track) {
        addReference(this, track);
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addAlbum(Album album) {
        addReference(this, album);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    @Override
    public String getInfo() {
        return getName() + " " + tracks.toString() + albums.toString();
    }

    @Override
    void addElement(Element element) {
        if (element.getClass() == Track.class && !tracks.contains(element)){
            tracks.add((Track) element);
        }
        if (element.getClass() == Album.class && !albums.contains(element)){
            albums.add((Album) element);
        }

    }
}
