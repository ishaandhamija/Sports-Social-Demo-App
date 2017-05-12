package com.example.ishaandhamija.sportssocialdemoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvList;
    RequestQueue requestQueue;
    ArrayList<Player> playerList;
    ArrayList<Player> mainPlayerList;

    public static final String TAG = "DD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerList = new ArrayList<>();
        mainPlayerList = new ArrayList<>();
        mainPlayerList.add(new Player("Ishaan","a","b",1,2,3,4,5));
        final Gson gson = new Gson();

        requestQueue = Volley.newRequestQueue(this);

        rvList = (RecyclerView) findViewById(R.id.rvList);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                "http://test.sportsocial.in/testfeed",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            JSONObject postJsonObj = null;
                            try {
                                postJsonObj = response.getJSONObject(i);
                                playerList.add(gson.fromJson(postJsonObj.toString(), Player.class));
                                mainPlayerList.add(new Player(playerList.get(i).getUser_name(), playerList.get(i).getActivity_name()
                                        , playerList.get(i).getGamename(), playerList.get(i).getIsPlaying()
                                        , playerList.get(i).getPlaymates(), playerList.get(i).getCommentCount()
                                        , playerList.get(i).getIsPromoting(), playerList.get(i).getWatchCount()));
                                mainPlayerList = getList(playerList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: ");
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);

        FeedAdapter feedAdapter = new FeedAdapter();

        rvList.setLayoutManager(new LinearLayoutManager(this));

        rvList.setAdapter(feedAdapter);
    }

    class FeedHolder extends RecyclerView.ViewHolder{

        CircleImageView profilePic;
        TextView name, timaAgo, activity, noOfPlayers, commentCount, watchCount;
        ImageView mainPic;
        LinearLayout playingWala, commentsWala, promoteWala, watchingWala;

        public FeedHolder(View itemView) {
            super(itemView);

            profilePic = (CircleImageView) findViewById(R.id.profilePic);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.timaAgo = (TextView) itemView.findViewById(R.id.timeAgo);
            this.activity = (TextView) itemView.findViewById(R.id.activity);
            this.noOfPlayers = (TextView) itemView.findViewById(R.id.noOfPlayers);
            this.commentCount = (TextView) itemView.findViewById(R.id.commentCount);
            this.watchCount = (TextView) itemView.findViewById(R.id.watchCount);
            mainPic = (ImageView) itemView.findViewById(R.id.mainPic);
            playingWala = (LinearLayout) itemView.findViewById(R.id.playingWala);
            commentsWala = (LinearLayout) itemView.findViewById(R.id.commentsWala);
            promoteWala = (LinearLayout) itemView.findViewById(R.id.promoteWala);
            watchingWala = (LinearLayout) itemView.findViewById(R.id.watchingWala);

        }
    }

    class FeedAdapter extends RecyclerView.Adapter<FeedHolder>{

        @Override
        public FeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View itemView = li.inflate(R.layout.player, parent, false);

            return new FeedHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FeedHolder holder, int position) {

            final Player player = mainPlayerList.get(position);

            holder.name.setText(player.getUser_name());

        }

        @Override
        public int getItemCount() {
            return mainPlayerList.size();
        }
    }

    public ArrayList<Player> getList(ArrayList<Player> List){
        return List;
    }

}
