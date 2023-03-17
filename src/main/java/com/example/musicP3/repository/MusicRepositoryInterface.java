package com.example.musicP3.repository;

import com.example.musicP3.entities.Music;
import org.springframework.data.repository.CrudRepository;

public interface MusicRepositoryInterface extends CrudRepository<Music, String> {

}
