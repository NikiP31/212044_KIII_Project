package com.example.kiii_app_212044.web;


import com.example.kiii_app_212044.model.Song;
import com.example.kiii_app_212044.service.SongService;
import com.example.kiii_app_212044.service.impl.SongServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = {"/", "/songs"})
    public String showList(@RequestParam(required = false) Double rating, @RequestParam(required = false) String artist, Model model) {
        List<Song> songs;
        if(rating==null && artist==null){
            songs=songService.findAll();
        }else {
            songs=songService.filter(artist,rating);
        }

        model.addAttribute("songs", songs);

        return "index";
    }
    @GetMapping("/addSong")
    public String addSongForm(Model model) {
        model.addAttribute("song", new Song());
        return "addSong";
    }

    @PostMapping("/addSong")
    public String addSongSubmit(@RequestParam String artist,
                                @RequestParam double rating,
                                @RequestParam String title,
                                @RequestParam String lyrics,
                                @RequestParam String imageUrl) {

        songService.create(artist,rating,title,lyrics,imageUrl);
        return "redirect:/songs";
    }
}
