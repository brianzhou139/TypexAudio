package com.sriyank.typexaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sriyank.typexaudio.models.singletons.myData;
import com.sriyank.typexaudio.models.songClass;

import java.util.ArrayList;

public class ArtistsAndGenreActivity extends AppCompatActivity {
    private static final String SONG_ARTIST_KEY="com.sriyank.typexaudio_artist";
    private static final String SONG_ARTISTANDGENRE_KEY="com.sriyank.typexaudio_artist_and_genre";
    private ArrayList<songClass> mySongList;
    private myData songlist=myData.getInstance();

    private ArrayList<String> artist_or_genre;
    ListView mListView;
    ArrayAdapter mAdapter;
    String userChoice=null;

    boolean isArtistDisplayed=false;
    boolean isGenreDisplayed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        //getting intent to check if user selectes genres or artists in the Mainactivity as this activity displays information conditinally
        Intent getData=getIntent();
        userChoice=getData.getStringExtra(SONG_ARTISTANDGENRE_KEY);
        mySongList=songlist.mySongList;
        artist_or_genre=new ArrayList<>();

        if(userChoice != null){
            getGenres();
        }else{
            getArtists();
        }

        mListView=(ListView)findViewById(R.id.listView_id);
        mAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,artist_or_genre);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                String selected=artist_or_genre.get(position);
                Intent openAllSongs=new Intent(getApplicationContext(), SongsActivity.class);
                if(isArtistDisplayed){
                    openAllSongs.putExtra(SONG_ARTIST_KEY,selected);
                }else{
                    openAllSongs.putExtra(SONG_ARTISTANDGENRE_KEY,selected);
                }
                startActivity(openAllSongs);
            }

        });
    }
    //method to get all the artists
    private void getArtists(){
        isArtistDisplayed=true;
        for(songClass row:mySongList){
            //check to see if an already exissts in the list
            String currentName=row.getArtist_name();
            boolean isPresent=false;

            for(String nom:artist_or_genre){
                if(nom.matches(currentName)){
                    isPresent=true;
                }
            }

            if(!isPresent){
                artist_or_genre.add(currentName);
            }
        }
    }
    //method to get all unique genres
    private void getGenres(){
        isGenreDisplayed=true;
        for(songClass row:mySongList){
            //check to see if an already exissts in the list
            String currentGenre=row.getCategory();
            boolean isPresent=false;

            for(String nom:artist_or_genre){
                if(nom.matches(currentGenre)){
                    isPresent=true;
                }
            }

            if(!isPresent){
                artist_or_genre.add(currentGenre);
            }
        }
    }

}
