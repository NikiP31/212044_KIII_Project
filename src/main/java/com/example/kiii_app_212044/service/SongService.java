package com.example.kiii_app_212044.service;

import com.example.kiii_app_212044.model.Song;

import java.util.Date;
import java.util.List;

public interface SongService {
    List<Song> findAll();

    Song findById(Long Id);

    Song create(String artist,Double rating,String title,String lyrics,String ImageURL);

    Song update(Long Id,String artist,Double rating,String title,String lyrics,String ImageURL);

    Song delete(Long Id);

    List<Song> filter(String Artist, Double rating);
}
