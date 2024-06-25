package com.example.kiii_app_212044.service.impl;

import com.example.kiii_app_212044.model.Song;
import com.example.kiii_app_212044.repository.SongRepository;
import com.example.kiii_app_212044.service.SongService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {

    SongRepository songRepository;

    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long Id) {
        return songRepository.findById  (Id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Song create(String artist, Double rating, String title, String lyrics, String ImageURL) {

        return songRepository.save(Song.builder().artist(artist).rating(rating).title(title).lyrics(lyrics).ImageURL(ImageURL).build());
    }

    @Override
    public Song update(Long Id, String artist, Double rating, String title, String lyrics, String ImageURL) {
        Song song= findById(Id);
        song.setArtist(artist);
        song.setRating(rating);
        song.setTitle(title);
        song.setLyrics(lyrics);
        song.setImageURL(ImageURL);
        return songRepository.save(song);
    }

    @Override
    public Song delete(Long Id) {
        Song song= findById(Id);
        songRepository.delete(song);
        return song;
    }

    @Override
    public List<Song> filter(String Artist, Double rating) {
        List<Song> songs=new ArrayList<>();
        if(Artist==null && rating==null) return findAll();
        if (Artist == null){
            return songRepository.findAll().stream().filter(s -> s.rating>=rating).collect(Collectors.toList());
        }
        if(rating==null){
            return  songRepository.findAll().stream().filter(x -> Objects.equals(x.artist, Artist)).collect(Collectors.toList());
        }
        return songRepository.findAll().stream().filter(x -> Objects.equals(x.artist, Artist) && x.rating>=rating).collect(Collectors.toList());
    }
}
