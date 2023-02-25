package com.example.eduapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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

public class RandomfunActivity extends YouTubeBaseActivity implements View.OnClickListener, YouTubePlayer.OnInitializedListener, NavigationView.OnNavigationItemSelectedListener {
    YouTubePlayerView mPlayer;
    NavigationView navigationView;
    String key="AIzaSyBj_1v1cZE1JXaogbXeLeY7WW-NUzDHmx8";
    int i;
    String ques_v;
    Button vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomfun);
        //n=(int) ((Math.random() * (10 - 0)) + 0);

        navigationView= findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);
        mPlayer= (YouTubePlayerView) findViewById(R.id.Youtube_viewer1);
        mPlayer.initialize(key, this);
        vb=(Button) findViewById(R.id.next1);
        vb.setOnClickListener(this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
            try {
                Intent my= getIntent();
                i=my.getIntExtra("score",i);
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
            InputStream in= this.getAssets().open("random.json");
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



    public void onClick(View v) {
        Button l= (Button) v;
        i++;
        if(i<10) {
            Intent myIntent = new Intent(this, RandomfunActivity.class);
            myIntent.putExtra("i", i);

            startActivity(myIntent);
        }
        else{
            i=0;
            Intent myIntent = new Intent(this, RandomfunActivity.class);
            myIntent.putExtra("i", i);

            startActivity(myIntent);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent= new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.quizz_mode:
                Intent intent1= new Intent(this, QuizzActivity.class);
                startActivity(intent1);
                break;
            case R.id.learn:
                break;
            default:
                throw new IllegalStateException("unknown choice"+ item.getItemId());
        }
        return true;
    }

}