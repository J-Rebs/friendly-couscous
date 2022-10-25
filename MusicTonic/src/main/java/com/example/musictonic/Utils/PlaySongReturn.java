package com.example.musictonic.Utils;

import java.sql.Timestamp;

public class PlaySongReturn {
    private Long id;
    private Timestamp timestamp;

    protected PlaySongReturn() {
    }

    ;

    public PlaySongReturn(Long id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
