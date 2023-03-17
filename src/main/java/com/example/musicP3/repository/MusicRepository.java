package com.example.musicP3.repository;

import com.example.musicP3.entities.Music;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;



    public List<Music> list(){
        return jdbcTemplate.query("SELECT MUSIC_ID, TITLE, DESCRIPTION FROM MUSIC",
                (rs, rowNum) -> new Music(
                    String.valueOf(rs.getLong("MUSIC_ID")),
                    rs.getString("TITLE"),
                    rs.getString("DESCRIPTION")
                ));
    }

    public void add(@NotNull Music music){
        jdbcTemplate.update("INSERT INTO MUSIC (TITLE, DESCRIPTION) VALUES(?,?)",
                new Object[]{
                    music.getTitle(), music.getDescription()
                });
    }

    public Music getId(String id){
        return  jdbcTemplate.queryForObject("SELECT * FROM MUSIC WHERE MUSIC_ID = ?", new Object[]{id},
                (rs, rowNum) -> new Music(
                        String.valueOf(rs.getLong("MUSIC_ID")),
                        rs.getString("TITLE"),
                        rs.getString("DESCRIPTION")
                ));
    }
    public void updateMusic(Music music){
         jdbcTemplate.update("UPDATE MUSIC SET TITLE = ?, DESCRIPTION = ? WHERE MUSIC_ID = ?",
            music.getTitle(), music.getDescription(), music.getMusic_id()
        );

    }

    }
