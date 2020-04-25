package com.sriyank.typexaudio.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sriyank.typexaudio.PlayerActivity;
import com.sriyank.typexaudio.R;
import com.sriyank.typexaudio.models.songClass;
import java.util.ArrayList;
import java.util.List;

public class songAdapter extends RecyclerView.Adapter<songAdapter.ViewHolder>  {

    private static final String SONG_CLICK_KEY="com.sriyank.typexaudio_song";
    private static final String SONG_POS_KEY="com.sriyank.typexaudio_pos";
    private Context context;
    private ArrayList<songClass> mySongList;
    private ArrayList<songClass> mySongListFiltered;

    public songAdapter(Context context, ArrayList<songClass> mySongList) {
        this.context = context;
        this.mySongList = mySongList;
        this.mySongListFiltered=mySongList;
    }
    @Override
    public songAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(songAdapter.ViewHolder holder, final int position) {

        holder.song_name.setText(mySongList.get(position).getSong_name());
        holder.singer_name.setText(mySongList.get(position).getArtist_name());
        holder.time.setText(mySongList.get(position).getTime()+":00");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //got to the PlayerActivity.....
                songClass clickedSong=mySongList.get(position);
                Intent openPlayer=new Intent(context, PlayerActivity.class);
                openPlayer.putExtra(SONG_CLICK_KEY,clickedSong);
                openPlayer.putExtra(SONG_POS_KEY,position);
                context.startActivity(openPlayer);
            }
        });

    }//end of onBindViewHolder...

    @Override
    public int getItemCount()   {
        return mySongListFiltered.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView song_name,singer_name,time;

        public ViewHolder(View view) {
            super(view);
            song_name =  view.findViewById(R.id.song_name);
            singer_name =  view.findViewById(R.id.singer_name);
            time =  view.findViewById(R.id.time);
        }
    }//end of ViewHolder

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mySongListFiltered = mySongList;
                } else {
                    ArrayList<songClass> filteredList = new ArrayList<>();

                    for (songClass row : mySongList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getArtist_name().toLowerCase().contains(charString.toLowerCase()) || row.getSong_name().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    mySongListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mySongListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mySongListFiltered = (ArrayList<songClass>) filterResults.values;
                notifyDataSetChanged();
            }

        };

    }///end of FilterYeah

}
