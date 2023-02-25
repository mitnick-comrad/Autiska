package com.example.eduapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Button Malaysian= findViewById(R.id.malaysian);
        Button English= findViewById(R.id.english);
        English.setOnClickListener(this);
        Malaysian.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.english: {
                Intent intent= new Intent(LanguageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            }
           /* case R.id.malaysian:{
                Intent intent= new Intent(LanguageActivity.this, Home_M_Activity.class);
                startActivity(intent);

            }*/
            default:{
                throw new IllegalStateException("unexpected value"+ v.getId());
            }
        }

    }
}