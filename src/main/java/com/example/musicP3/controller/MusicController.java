package com.example.musicP3.controller;

import com.example.musicP3.entities.Music;
import com.example.musicP3.form.CreateMusicForm;
import com.example.musicP3.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;
    @RequestMapping("/")
    public String displayHome(){
        return "index";
    }
    @RequestMapping("/list")
    public @ModelAttribute("musics") List<Music> displayList(){
        System.out.println("Je suis dans list");
        List<Music> musics = musicRepository.list();
        return musics;
    }
    @GetMapping("/add-music")
    public String displayAdd(@ModelAttribute CreateMusicForm createMusicForm){
        return "createMusic";
    }
    @GetMapping("/view/{id}")
    public String displayView(@PathVariable("id") String id, Model model, Music musicEntity, RedirectAttributes redirectAttributes){
        try{
            Music music = musicRepository.getId(id);
            model.addAttribute("music", music);
            return "viewMusic";
        } catch (DataAccessException e){
            e.printStackTrace();
        }
        return "index";

    }
    @PostMapping("/create-music")
    public String createMusic(@ModelAttribute CreateMusicForm createMusicForm){
        System.out.println("Controller create");
        Music music = new Music();
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.add(music);

        return "successForm";
    }
    @GetMapping("/edit-music/{id}")
    public String displayUpdateMusic(@PathVariable("id") String id, Model model){

        System.out.println("Hello from UPDATE1");
            Music music = musicRepository.getId(id);
            CreateMusicForm createMusicForm = new CreateMusicForm();
            createMusicForm.setTitle(music.getTitle());
            createMusicForm.setDescription(music.getDescription());
            model.addAttribute("createMusicForm", music);
            return "edit-music";

    }

    @PostMapping("/editMusic/{id}")
    public String edit(@PathVariable("id") String id, @ModelAttribute CreateMusicForm createMusicForm){

        System.out.println("Hello from UPDATE2");
        Music music = musicRepository.getId(id);
        music.setTitle(createMusicForm.getTitle());
        music.setDescription(createMusicForm.getDescription());
        musicRepository.updateMusic(music);
        return "successUpdateMusic";

    }


}

