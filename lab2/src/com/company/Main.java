package com.company;

import static com.company.Genre.*;

public class Main {

    public static void main(String[] args) {
        Artist artist1 = new Artist("Bz bz");
        Artist artist2 = new Artist("Linkin park");
        Artist artist3 = new Artist("Leo");
        Track track1 = new Track("WOW No hook");
        Track track2 = new Track("Wus hatneen");
        Track track3 = new Track("Figure");
        Track track4 = new Track("joint track");
        Track track5 = new Track("Super");

        track3.setGenre(ALTERNATIVEROCK);
        artist1.addTrack(track4);
        artist2.addTrack(track4);
        artist1.addTrack(track5);
        Album album4 = new Album("Joint album");
        album4.addTrack(track4);
        artist1.addAlbum(album4);

        track1.addArtist(artist1);
        artist1.addTrack(track2);
        artist2.addTrack(track3);

        Album album1 = new Album("Main eff");
        Album album2 = new Album("Meteora");
        Album album3 = new Album("Hybrid Theory");
        album2.setGenre(ALTERNATIVEROCK);
        album3.setGenre(ALTERNATIVEROCK);

        artist1.addAlbum(album1);
        artist2.addAlbum(album3);
        Track track6 = new Track("One step closer");
        track6.setGenre(HARDROCK);
        album3.addTrack(track6);
        album3.addTrack(new Track("In the end"));
        album1.addTrack(track1);
        album2.addTrack(track3);
        album2.addArtist(artist2);

        System.out.println(artist1.getInfo());
        System.out.println(artist2.getInfo());

        PlayList playList1 = new PlayList("New tracks");
        playList1.addTrack(track4); playList1.addTrack(track5);
        playList1.addTrack(artist1.getTracks());

        Album album5 = new Album("Leo's album");
        album5.addArtist(artist3);
        Catalog catalog = Catalog.getInstance();
        catalog.addAlbum(artist1.getAlbums());
        catalog.addAlbum(artist2.getAlbums());
        catalog.addAlbum(artist3.getAlbums());
        catalog.addPlayList(playList1);
        System.out.println("Albums in catalog: " + catalog.getAlbums() + "\n");
        System.out.println("PlayLists in catalog: " + catalog.getPlayLists());

        //альбомы в каталоге
        for (Album album : catalog.getAlbums())
            System.out.println(album.getInfo());

        //треки в альбомах из каталога
        for (Album album : catalog.getAlbums()){
            for (Track track : album.getTracks()){
                System.out.println(track.getInfo());
            }
        }
        //треки из плейлиста
        System.out.println();
        for (PlayList playList : catalog.getPlayLists()){
            for (Track track : playList.getTracks()){
                System.out.println(track.getInfo());
            }
        }

        System.out.println();
        System.out.println(catalog.findArtist("L"));
        System.out.println(catalog.findAlbumsOrPlaylist("Me"));
        System.out.println(catalog.findTracks(new Track(""), ROCK));
    }

}
