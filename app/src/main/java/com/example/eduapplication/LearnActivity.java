package com.example.eduapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static com.example.eduapplication.R.layout.activity_learn;

public class LearnActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    YouTubePlayerView mPlayer;
    NavigationView navigationView;
    String key="AIzaSyBj_1v1cZE1JXaogbXeLeY7WW-NUzDHmx8";
    int i;
    Button view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_learn);

        navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent my= getIntent();
        i=my.getIntExtra("i",i);
        //if(i==1)
          //  i++;
        mPlayer= (YouTubePlayerView) findViewById(R.id.Youtube_viewer);
        mPlayer.initialize(key, this);
        view= (Button) findViewById(R.id.next);
        view.setOnClickListener(this);


    }
    String ques_v;

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {




        if(!wasRestored){
            try {

                JSONObject obj = new JSONObject(Loadfromassets());
                JSONArray array = obj.getJSONArray("intents");
                JSONObject o = array.getJSONObject(i);
                ques_v = o.getString("url");
                youTubePlayer.cueVideo(ques_v);
                youTubePlayer.play();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public String Loadfromassets(){
        String json= null;
        try{
            InputStream in= this.getAssets().open("learning.json");
            int size= in.available();
            byte[] buffer= new byte[size];
            in.read(buffer);
            in.close();
            json= new String(buffer,"UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent= new Intent(LearnActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.quizz_mode:
                Intent intent1= new Intent(LearnActivity.this, QuizzActivity.class);
                startActivity(intent1);
                break;
            case R.id.learn:
                break;
            default:
                throw new IllegalStateException("unlknown choice"+ item.getItemId());
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Button l= (Button) v;
        i=i+1;
        if(i<10) {
            Intent myIntent = new Intent(this, LearnActivity.class);
            myIntent.putExtra("i", i);

            startActivity(myIntent);
            finish();
        }
        else{
            i=0;
            Intent myIntent = new Intent(this, LearnActivity.class);
            myIntent.putExtra("i", i);

            startActivity(myIntent);
        }

    }
}