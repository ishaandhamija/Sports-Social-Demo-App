package com.example.ishaandhamija.sportssocialdemoapp;

/**
 * Created by ishaandhamija on 08/05/17.
 */

public class Player {

    String user_name;
    String Activity_name;
    String gamename;
    Integer IsPlaying;
    Integer playmates;
    Integer CommentCount;
    Integer IsPromoting;
    Integer WatchCount;

    public Player(String user_name, String activity_name, String gamename, Integer isPlaying, Integer playmates, Integer commentCount, Integer isPromoting, Integer watchCount) {
        this.user_name = user_name;
        Activity_name = activity_name;
        this.gamename = gamename;
        IsPlaying = isPlaying;
        this.playmates = playmates;
        CommentCount = commentCount;
        IsPromoting = isPromoting;
        WatchCount = watchCount;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getActivity_name() {
        return Activity_name;
    }

    public String getGamename() {
        return gamename;
    }

    public Integer getIsPlaying() {
        return IsPlaying;
    }

    public Integer getPlaymates() {
        return playmates;
    }

    public Integer getCommentCount() {
        return CommentCount;
    }

    public Integer getIsPromoting() {
        return IsPromoting;
    }

    public Integer getWatchCount() {
        return WatchCount;
    }
}
