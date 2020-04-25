package com.sriyank.typexaudio.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kuldeep on 13/02/18.
 */

public class songClass implements Parcelable {

    private String song_name,artist_name,time,category;

    public songClass(){}

    public songClass(String song_name, String artist_name, String time, String category) {
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.time = time;
        this.category = category;
    }

    protected songClass(Parcel in) {
        song_name = in.readString();
        artist_name = in.readString();
        time = in.readString();
        category = in.readString();
    }

    public static final Creator<songClass> CREATOR = new Creator<songClass>() {
        @Override
        public songClass createFromParcel(Parcel in) {
            return new songClass(in);
        }

        @Override
        public songClass[] newArray(int size) {
            return new songClass[size];
        }
    };

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(song_name);
        parcel.writeString(artist_name);
        parcel.writeString(time);
        parcel.writeString(category);
    }

}///end of songClass_yeah
