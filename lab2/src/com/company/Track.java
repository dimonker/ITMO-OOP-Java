package com.company;

import java.util.ArrayList;

public class Track extends Element {
    private ArrayList<Artist> artists = new ArrayList<>();
    private Album album = new Album("no album");
    private Genre genre = Genre.NOGENRE;

    public Track(String name) {
        super(name);
    }

    public void addArtist(Artist artist) {
        addReference(this, artist);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void addAlbum(Album album) {
        addReference(this, album);
    }

    public Album getAlbum() {
        return album;
    }

    @Override
    public String getInfo() {
        return getName() + ", " + genre + ", " +
                artists.toString() + ", " + album;
    }

    @Override
    void addElement(Element element) {
        if (element.getClass() == Artist.class && !artists.contains(element)){
            artists.add((Artist) element);
        }
        if (element.getClass() == Album.class && album != element){
            album = (Album) element;
        }
    }

    public Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
