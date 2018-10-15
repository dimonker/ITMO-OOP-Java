package com.company;

import java.util.ArrayList;
import java.util.HashSet;


public class Catalog {
    private static Catalog instance = new Catalog();

    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<PlayList> playLists = new ArrayList<>();

    private Catalog(){}

    public static Catalog getInstance(){
        return instance;
    }

    public void addAlbum(Album album){
        if (!albums.contains(album))
            albums.add(album);
    }

    public void addAlbum(ArrayList<Album> albums){
        for (Album album : albums){
            addAlbum(album);
        }
    }

    public void addPlayList(PlayList playList){
        if (!playLists.contains(playList))
            playLists.add(playList);
    }

    public ArrayList<PlayList> getPlayLists(){
        return playLists;
    }

    public ArrayList<Album> getAlbums(){
        return albums;
    }

    private void findAllTrack(HashSet<Track> allTracks){
        for (Album album : albums){
            allTracks.addAll(album.getTracks());
        }
        for (PlayList playList : playLists){
            allTracks.addAll(playList.getTracks());
        }
    }

    private void findSubGenres(ArrayList<Genre> genres, Genre genre){
        Genre[] children = genre.getChildren();
        genres.add(genre);
        for (int i = 0; i < children.length; i++){
            findSubGenres(genres, children[i]);
        }
    }

    public ArrayList<Track> findTracks(Track track, Genre genre){
        HashSet<Track> allTrack = new HashSet<>();
        ArrayList<Genre> subGenre = new ArrayList<>();
        findAllTrack(allTrack);
        findSubGenres(subGenre, genre);
        //System.out.println("ALL GENRES " + subGenre);
        ArrayList<Track> result = new ArrayList<>();
        for (Track t : allTrack){
            //System.out.println(subGenre.toString() + " " + t.getGenre().toString());
            if (t.getName().toLowerCase().contains(track.getName().toLowerCase())
                    && subGenre.toString().contains(t.getGenre().toString())){
                result.add(t);
            }
        }

        return result;
    }

    public ArrayList<Element> findAlbumsOrPlaylist(String request){
        ArrayList<Element> all = new ArrayList<>();
        all.addAll(albums);
        all.addAll(playLists);
        ArrayList<Element> result = new ArrayList<>();
        for (Element element : all){
            if (element.getName().toLowerCase().contains(request.toLowerCase())){
                result.add(element);
            }
        }
        return result;
    }

    public ArrayList<Artist> findArtist(String request){
        HashSet<Artist> allArtist = new HashSet<>();
        for (Album album : albums){
            allArtist.addAll(album.getArtists());
        }

        ArrayList<Artist> result = new ArrayList<>();
        for (Artist artist : allArtist){
            if (artist.toString().toLowerCase().contains(request.toLowerCase())){
                result.add(artist);
            }
        }

        return result;
    }
}
