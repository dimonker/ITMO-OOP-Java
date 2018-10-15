package com.company;

import java.util.ArrayList;

public class Album extends Element {
    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();
    private Genre genre = Genre.NOGENRE;

    public Album(String name) {
        super(name);
    }

    public void addArtist(Artist artist) {
        addReference(this, artist);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addTrack(Track track) {
        addReference(this, track);
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    @Override
    public String getInfo() {
        return getName() + ", " +
                genre + ", " +
                artists.toString() + ", " + tracks.toString();
    }

    @Override
    void addElement(Element element) {
        if (element.getClass() == Artist.class && !artists.contains(element)) {
            artists.add((Artist) element);
            for (Track track : tracks) {
                ((Artist) element).addTrack(track);
            }
        }
        if (element.getClass() == Track.class && !tracks.contains(element)) {
            tracks.add((Track) element);
            for (Artist artist : artists) {
                ((Track) element).addArtist(artist);
            }
            for (Artist artist : ((Track) element).getArtists()) {
                this.addArtist(artist);
            }
        }
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
