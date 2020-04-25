package com.sriyank.typexaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyank.typexaudio.models.singletons.myData;
import com.sriyank.typexaudio.models.songClass;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    private static final String SONG_CLICK_KEY="com.sriyank.typexaudio_song";
    private static final String SONG_POS_KEY="com.sriyank.typexaudio_pos";
    private myData songlist=myData.getInstance();
    private ArrayList<songClass> mySongList;
    songClass currentSong=new songClass();

    TextView mTitle,mName,mTime;
    SeekBar mSeekBar;
    CountDownTimer myTimer;
    int progress_init=0;
    private Double mGetStime;

    ImageView mPause,mNext,mBack;
    boolean isPlaying=true;
    int currentSongPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mySongList=songlist.mySongList;

        Intent getInfor=getIntent();
        currentSong=getInfor.getParcelableExtra(SONG_CLICK_KEY);
        currentSongPosition=getInfor.getIntExtra(SONG_POS_KEY,0);
        initialization();
        myTimer.start();
    }//end of onCreate.
    //method to initialize views and onClickListener implementations
    private void initialization(){
        mPause=(ImageView)findViewById(R.id.pause_id);
        mBack=(ImageView)findViewById(R.id.back_id);
        mNext=(ImageView)findViewById(R.id.next_id);
        mTitle=(TextView)findViewById(R.id.song_title_id);
        mName=(TextView)findViewById(R.id.song_artist_id);
        mTime=(TextView)findViewById(R.id.time_id);
        mSeekBar=(SeekBar)findViewById(R.id.seek_bar_id);
        ///set the current song
        mTitle.setText(currentSong.getSong_name());
        mName.setText(currentSong.getArtist_name());
        mTime.setText(currentSong.getTime()+":00");

        int mMax=Integer.parseInt(currentSong.getTime());
        int MaxSeconds=mMax*60000;
        mSeekBar.setMax(MaxSeconds);
        mGetStime = Double.valueOf(currentSong.getTime());

        //imageClicks
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    mPause.setImageResource(R.drawable.ic_play_circle_filled);
                    myTimer.cancel();
                    isPlaying=false;
                }else{
                    mPause.setImageResource(R.drawable.ic_pause_white);
                    myTimer.start();
                    isPlaying=true;
                }

            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToPrev();
                myTimer.cancel();
                myTimer.start();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeToNext();
                myTimer.cancel();
                myTimer.start();
            }
        });

        myTimer=new CountDownTimer(MaxSeconds,1000) {
            @Override
            public void onTick(long l) {
                progress_init+=1000;
                mSeekBar.setProgress(progress_init);
                setTimeCountDown();
            }
            @Override
            public void onFinish() {
                changeToNext();
                myTimer.cancel();
                myTimer.start();
            }
        };
    }
    //method to set the time string as the song is playing , so the textView must decrease to show impression of a playing song on Countdown
    private void setTimeCountDown() {
        mGetStime=mGetStime-0.01;
        String strDouble = String.format("%.2f",mGetStime);
        mTime.setText(strDouble);
    }
    //method to move to the prevous song
    private void changeToPrev(){
        if(currentSongPosition==0){
            currentSongPosition=mySongList.size()-1;
        }else{
            currentSongPosition--;
        }

        playPreOrNext(currentSongPosition);
    }
    ///method to move to the next song
    private void changeToNext(){
        if(currentSongPosition==mySongList.size()-1){
            currentSongPosition=0;
        }else{
            currentSongPosition++;
        }
        playPreOrNext(currentSongPosition);
    }
    //method to set the next or prevous song according to what the user presses
    private void playPreOrNext(int position){
        int mMax=Integer.parseInt(currentSong.getTime());
        int MaxSeconds=mMax*60000;
        mGetStime = Double.valueOf(currentSong.getTime());
        mSeekBar.setMax(MaxSeconds);
        progress_init=0;
        mSeekBar.setProgress(0);
        mTitle.setText(mySongList.get(position).getSong_name());
        mName.setText(mySongList.get(position).getArtist_name());
        mTime.setText(mySongList.get(position).getTime());
    }

}
