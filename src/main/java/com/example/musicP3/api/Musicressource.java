package com.example.musicP3.api;

import com.example.musicP3.entities.Music;
import com.example.musicP3.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class Musicressource {
    @Autowired
    private MusicRepository musicRepository;
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("apiList")
    public @ResponseBody List<Music> list(){
        return (List<Music>) musicRepository.list();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Optional<Music> get(@PathVariable("id") String number){
        return Optional.ofNullable(musicRepository.getId(number));
    }

}
