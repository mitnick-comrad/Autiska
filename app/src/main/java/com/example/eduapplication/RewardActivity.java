package com.example.eduapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;

public class RewardActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    int score;
    Animation topAnim;
    NavigationView navigationView;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        Intent my= getIntent();
        score=my.getIntExtra("score",score);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        //image = findViewById(R.id.imageView2);
        //image.setAnimation(topAnim);
        ImageView fi= findViewById(R.id.imageView4);
        TextView ts= findViewById(R.id.reward);
        ts.setText("Score : "+score);
        ts.setAnimation(topAnim);
        fi.setAnimation(topAnim);
        ImageView t= findViewById(R.id.Cong);
        t.setImageResource(R.drawable.congrats);
        t.setAnimation(topAnim);
        ImageView back= findViewById(R.id.bak);
        back.setOnClickListener(this);
        navigationView = findViewById(R.id.nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public String Loadfromassets(){
        String json= null;
        try{
            InputStream in= this.getAssets().open("img.json");
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
    public void onClick(View v) {
        if(v.getId()== R.id.bak)
        {
            Intent myIntent = new Intent(this, HomeActivity.class);
            //myIntent.putExtra("i", 0);
            startActivity(myIntent);
            finish();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent= new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
            case R.id.quizz_mode:
                break;
            default:
                throw new IllegalStateException("unknown selection"+ item.getItemId());
        }
        return true;
    }
}