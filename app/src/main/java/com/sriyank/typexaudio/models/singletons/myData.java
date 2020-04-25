package com.sriyank.typexaudio.models.singletons;

import com.sriyank.typexaudio.models.songClass;

import java.util.ArrayList;

public class myData {

    public ArrayList<songClass> mySongList;
    public ArrayList<String> qoutes;
    private static myData sSoleInstance;

    private myData(){
        this.mySongList=new ArrayList<>();
        this.qoutes=new ArrayList<>();
        mySongList=fillInitSongs();
        qoutes=fillInitQoute();
    }

    private myData(ArrayList<songClass> mySongList) {
        this.mySongList = mySongList;
    }

    public static myData getInstance(){

        if (sSoleInstance == null){ //if there is no instance available... create new one
            sSoleInstance = new myData();
        }

        return sSoleInstance;

    }

    private ArrayList<songClass> fillInitSongs(){
        ArrayList<songClass> tempSongList =new ArrayList<>();

        tempSongList.add(new songClass("The Twist","Chubby Checker","2","pop"));
        tempSongList.add(new songClass("Smooth"," Santana","3","pop"));
        tempSongList.add(new songClass("Mack the Knife","Bobby Darin","2","rock"));
        tempSongList.add(new songClass("Uptown Funk","Mark Ronson","4","rock"));
        tempSongList.add(new songClass("How Do I Live","Leann Rimes","3","pop"));
        tempSongList.add(new songClass("Party Rock Anthem","LMFAO","2","pop"));
        tempSongList.add(new songClass("I Gotta Feeling","Black Eyed Peas ","4","hippop"));
        tempSongList.add(new songClass("Macarena","Los Del Rio (1996)","5","hippop"));
        tempSongList.add(new songClass("Shape of You","Ed Sheeran (2017)","3","hippop"));
        tempSongList.add(new songClass("Physical","Olivia Newton-John","3","hippop"));
        tempSongList.add(new songClass("Tossin and Turnin","Bobby Lewis","4","hippop"));
        tempSongList.add(new songClass("The Battle of New Orleans","Johnny Horton","3","hippop"));
        tempSongList.add(new songClass("Let’s Get It On","Marvin Gaye","2","rap"));
        tempSongList.add(new songClass("Night Fever ","Bee Gees","3","rap"));
        tempSongList.add(new songClass("How You Remind Me","Nickelback","2","rap"));
        tempSongList.add(new songClass("Tie a Yellow Ribbon Round the Ole Oak Tree ","Dawn Feat. Tony Orlando","3","hippop"));
        tempSongList.add(new songClass(" I Want to Hold Your Hand","The Beatles","3","afro"));
        tempSongList.add(new songClass("Shadow Dancing","Andy Gibb","4","afro"));
        tempSongList.add(new songClass("Call Me Maybe","Carly Rae Jepsen","2","afro"));
        tempSongList.add(new songClass(" No One ","Alicia Keys","5","afro"));
        tempSongList.add(new songClass(" I Will Always Love You","Whitney Houston","3","rap"));
        tempSongList.add(new songClass(" End of the Road","Boyz II Men","3","rap"));
        tempSongList.add(new songClass("Let Me Love You","Mario","2","rap"));
        tempSongList.add(new songClass("Stayin’ Alive ","Bee Gees","3","rap"));
        tempSongList.add(new songClass("Lady","Kenny Rogers","4","afro"));
        tempSongList.add(new songClass("The Sign","Ace Of Base","3","rap"));
        tempSongList.add(new songClass("Centerfold","The J. Geils Band","4","rap"));
        tempSongList.add(new songClass("God's Plan","drake","5","afro"));
        tempSongList.add(new songClass("Clean Bendit","Symponi ft. Zavra","2","groove"));
        tempSongList.add(new songClass("Taylor Swift","Clean Bendit","4","hippop"));
        tempSongList.add(new songClass("Look What You Made Me Do","Clean Bendit","4","hippop"));
        tempSongList.add(new songClass("Boda Yello","Clean Bendit","2","groove"));
        return  tempSongList;
    }

    private ArrayList<String> fillInitQoute(){
        ArrayList<String> temp_qoutes=new ArrayList<>();
        temp_qoutes.add("Music is like a dream. One that I cannot hear");
        temp_qoutes.add("Where words fail, music speaks");
        temp_qoutes.add("Who hears music, feels his solitude");
        temp_qoutes.add("Music is the strongest form of magic");
        temp_qoutes.add("Music in the soul can be heard by the universe");
        temp_qoutes.add("Music is an outburst of the soul");
        temp_qoutes.add("If music be the food of love, play on");
        temp_qoutes.add("If I cannot fly, let me sing");
        temp_qoutes.add("Music, when soft voices die, vibrates in the memory");
        temp_qoutes.add("Music is my higher power");
        temp_qoutes.add("Music is an outburst of the soul");
        temp_qoutes.add("Music is the wine that fills the cup of silence");
        temp_qoutes.add("The music is not in the notes, but in the silence between");
        temp_qoutes.add("I like beautiful melodies telling me terrible things");
        return temp_qoutes;
    }

}//end of myDataSingleton




