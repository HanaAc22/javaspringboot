package com.example.musicP3.service;

import com.example.musicP3.entities.Music;
import com.example.musicP3.repository.MusicRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService {
    @Autowired
    private MusicRepositoryInterface musicRepositoryInterface;
    public Iterable<Music> getMusicList(){
        return musicRepositoryInterface.findAll();
    }
}
