package com.example.eduapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class QuizzActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    TextView quiz;
    boolean correct;
    String ques_v,ans;
    int i,score;
    TextToSpeech textToSpeech;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        textToSpeech= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!= TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        setContentView(R.layout.activity_quizz);
        Button back = findViewById(R.id.back);
        quiz=findViewById(R.id.textView3);
         option1 = findViewById(R.id.button);
         option2 = findViewById(R.id.button1);
         option3 = findViewById(R.id.button2);
         option4 = findViewById(R.id.button3);
         TextView scor= findViewById(R.id.textView4);
         //ImageView im= findViewById(R.id.imageView3);
         score= i*2;


         //String smt="Score: "+ k;



        /*if(correct){
            i++;



        }*/

        //while (i<10) {

            back.setOnClickListener(this);
            option1.setOnClickListener(this);
            option2.setOnClickListener(this);
            option3.setOnClickListener(this);
            option4.setOnClickListener(this);
            try {
                int[] re= new int[]{R.drawable.dog,R.drawable.cow,R.drawable.leaf,R.drawable.elephant,R.drawable.apple,R.drawable.car,R.drawable.tiger,R.drawable.rose,R.drawable.owl,R.drawable.strawberries};


                JSONObject obj = new JSONObject(Loadfromassets());
                JSONObject obj1 = new JSONObject(Loadfromassets1());
                //JSONArray array1= obj1.getJSONArray("i");
                //String u= array1.getString(0);
                //i= Integer.parseInt(u);
                Intent mIntent = getIntent();
                 i = mIntent.getIntExtra("i", i);
                 //score= mIntent.getExtras("score",score);
                score=i*2;
                scor.setText("Score:"+score);


                ImageView im= findViewById(R.id.imageView3);
                if (i<re.length)
                    im.setImageResource(re[i]);
                JSONArray array = obj.getJSONArray("intents");
                HashMap<String, String> list;
                HashMap<String, String[]> opt;
                //HashMap<String,String[]>opt2;
                ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                ArrayList<HashMap<String, String[]>> arrayList1 = new ArrayList<>();
                //for( int i=0;i<array.length();i++){
                //if (i < array.length()) {
                    JSONObject o = array.getJSONObject(i);
                    ques_v = o.getString("question");
                    ans = o.getString("answer");



                    //JSONObject jsonData = o.optJSONObject("option");
                    JSONArray arrJson = o.getJSONArray("option");
                    String[] op = new String[arrJson.length()];
                    for (int j = 0; j < 4; j++)//arrJson.length()
                        op[j] = arrJson.getString(j);
                    option1.setText(op[0]);
                    option2.setText(op[1]);
                    option3.setText(op[2]);
                    option4.setText(op[3]);
                    quiz.setText(ques_v);
                textToSpeech= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status!= TextToSpeech.ERROR){
                             textToSpeech.setLanguage(Locale.US);
                        }
                    }
                });
                textToSpeech.setSpeechRate(0.1f);
                textToSpeech.speak(quiz.getText().toString(),TextToSpeech.QUEUE_FLUSH,null, null);





                    //list=new HashMap<>();
                    //opt2= new HashMap<>();
                    //opt= new HashMap<>();
                    //opt.put("option",op);
                    //opt2.put("option",op);
                    //list.put("question",ques_v);
                    //list.put("answer",ans);
                    //arrayList.add(list);
                    //arrayList1.add(opt);

                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                    option4.setEnabled(true);
                    //for(int i=0; i<array)
                    if(i>=10){
                        //i=0;
                        Intent myIntent = new Intent(this, RewardActivity.class);
                        myIntent.putExtra("score", score);
                        startActivity(myIntent);
                        finish();
                        //String l= "{i:"+ String.valueOf(i) +"]}";


                        /*try {
                            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
                            FileWriter file = new FileWriter(path+"Jsont.json");
                            file.write(l);
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                    }




                //}


                //Set<String> keySet = arrayList1.va
            /*for (int i=0; i<array.length();i++){
                for (String ) {
                    String[] r= arrayList1.get(j);

                    option1.setText();
                }
            }*/
            } catch (JSONException e) {
                e.printStackTrace();
            }


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //}
        super.onCreate(savedInstanceState);



    }
    boolean isCorrect(boolean a){
        return a;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //


            case R.id.button1:
                //

            case R.id.button2:
                //

            case R.id.button3:
                //
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
               Button b= (Button) v;

               if(((Button) v).getText().equals(ans))
               {  b.setText("Correct!");
                  b.setBackgroundColor(-204);
                  correct=true;
                  i++;
                  //String l= "{i:"+ String.valueOf(i) +"]}";


                   /*String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
                   FileWriter file = new FileWriter(path+"Jsont.json");
                   file.write(l);
                   file.close();*/
                   if(i<10) {
                       Intent myIntent = new Intent(this, QuizzActivity.class);
                       myIntent.putExtra("i", i);
                       myIntent.putExtra("score", (i * 2));
                       startActivity(myIntent);
                       finish();
                   }
                   else{
                       Intent myIntent = new Intent(this, RewardActivity.class);
                       score=i*2;

                       myIntent.putExtra("score", score);
                       startActivity(myIntent);
                       finish();
                   }


               }
               else
               {
                   b.setText("Wrong, Try again!");
                   b.setBackgroundColor(255);
                   correct=false;
                   Intent myIntent = new Intent(this, QuizzActivity.class);
                   myIntent.putExtra("i", i);
                   myIntent.putExtra("score",score);
                   startActivity(myIntent);
                   finish();
               }
                /*Intent intent =new Intent(this, QuizzActivity.class);
                startActivity(intent);
                finish();*/


               //b.setText("Select");


                break;
            case R.id.back:
                Intent intent2= new Intent(QuizzActivity.this, HomeActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected choice"+ v);

        }
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
    public String Loadfromassets1(){
        String json= null;
        try{
            InputStream in= this.getAssets().open("Jsont.json");
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent= new Intent(QuizzActivity.this, HomeActivity.class);
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