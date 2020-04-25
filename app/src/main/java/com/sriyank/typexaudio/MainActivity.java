package com.sriyank.typexaudio;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sriyank.typexaudio.models.singletons.myData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String SONG_ARTISTANDGENRE_KEY="com.sriyank.typexaudio_artist_and_genre";
    ImageView mAllSongs,mGenres,mArtists,mEqaulizer,mTheme,mSettings;
    private ArrayList<String> myQouteList;
    private myData qoutes=myData.getInstance();
    private String currentQoute;
    private TextView mQoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializationMain();
    }
    //method to initialize view and onClickListeners implementation
    private void InitializationMain(){
        mAllSongs=(ImageView)findViewById(R.id.allMusic_id);
        mGenres=(ImageView)findViewById(R.id.genres_id);
        mArtists=(ImageView)findViewById(R.id.artists_id);
        mEqaulizer=(ImageView)findViewById(R.id.equalizer_id);
        mTheme=(ImageView)findViewById(R.id.theme_id);
        mSettings=(ImageView)findViewById(R.id.settings_id);
        mQoute=(TextView)findViewById(R.id.qoute_id);
        setRandomQoute();

        mAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAllSongs=new Intent(getApplicationContext(), SongsActivity.class);
                startActivity(openAllSongs);
            }
        });

        mGenres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openArtistsAndGenre=new Intent(getApplicationContext(), ArtistsAndGenreActivity.class);
                openArtistsAndGenre.putExtra(SONG_ARTISTANDGENRE_KEY,"genre");
                startActivity(openArtistsAndGenre);
            }
        });

        mArtists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openArtistsAndGenre=new Intent(getApplicationContext(), ArtistsAndGenreActivity.class);
                startActivity(openArtistsAndGenre);
            }
        });

        mEqaulizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Tunes",Toast.LENGTH_SHORT).show();
            }
        });

        mTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Themes",Toast.LENGTH_SHORT).show();
            }
        });

        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
            }
        });

    }
    //method to set random qoute to display on the MainActivity
    private void setRandomQoute(){
        myQouteList=qoutes.qoutes;
        currentQoute=getRandomElement(myQouteList);
        mQoute.setText(currentQoute);
    }
    //method to generate and return a random qoute
    public String getRandomElement(List<String> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}//end of MainActivity..
