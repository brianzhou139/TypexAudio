package com.sriyank.typexaudio;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sriyank.typexaudio.adapters.songAdapter;
import com.sriyank.typexaudio.models.singletons.myData;
import com.sriyank.typexaudio.models.songClass;
import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {
    private static final String SONG_ARTIST_KEY="com.sriyank.typexaudio_artist";
    public ImageView Image;
    private RecyclerView rv;
    private ArrayList<songClass> mySongList;
    private myData songlist=myData.getInstance();
    private songAdapter listAdapter;
    private  String selectedArtistOrGenre=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allsongs);
        mySongList=songlist.mySongList;
        Intent getData=getIntent();
        selectedArtistOrGenre=getData.getStringExtra(SONG_ARTIST_KEY);

        if(selectedArtistOrGenre != null){
            mySongList=getArtistOrGenreSongs(selectedArtistOrGenre);
        }

        rv = (RecyclerView) findViewById(R.id.rv);
        listAdapter = new songAdapter(SongsActivity.this, mySongList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(SongsActivity.this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(listAdapter);
    }
    //method to inflate a  menu that shows a searchview
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflator=getMenuInflater();
        mInflator.inflate(R.menu.my_option_menu,menu);
        return true;
    }
    //method to get userTyped string on the searchView
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchViewMenuItem = menu.findItem(R.id.search_id);
        SearchView searchView = (SearchView) searchViewMenuItem.getActionView();

        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }
    //method to get reference to menu views and implement actions onClick events
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.help_id:
                Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about_id:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //method to get the songs by a certain artists or songs of a specific genre
    private ArrayList<songClass> getArtistOrGenreSongs(String targ){
        ArrayList<songClass> resultSongs=new ArrayList<>();

        for(songClass row:mySongList){
            if(targ.matches(row.getArtist_name()) || targ.matches(row.getCategory())){
                resultSongs.add(row);
            }
        }

        return resultSongs;
    }

}//end of onCreate..
