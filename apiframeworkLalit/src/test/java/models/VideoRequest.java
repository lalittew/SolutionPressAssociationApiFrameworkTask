package models;

import gherkin.deps.com.google.gson.Gson;

import java.util.Date;

//Getter and setter methods to post the request to server
public class VideoRequest implements SongRequest{

    private String artist;
    private String song;
    private Date publishDate;

    public VideoRequest(String artist, String song, Date publishDate) {
        this.artist = artist;
        this.song = song;
        this.publishDate = publishDate;
    }

    public String getArtist() {
        return artist;
    }


    public String getSong() {
        return song;
    }


    public Date getPublishDate() {
        return publishDate;
    }


    @Override
    public String toJsonString() {
        return new Gson().toJson(this);
    }
}
