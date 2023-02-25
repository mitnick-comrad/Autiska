package com.example.eduapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;


import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, YouTubePlayer.OnInitializedListener {

    private static final String TAG = "Recorder";
    public static SurfaceView mSurfaceView;
    public static SurfaceHolder mSurfaceHolder;
    public static Camera mCamera ;
    public static boolean mPreviewRunning;

    YouTubePlayerView mPlayer;
    String key="AIzaSyBj_1v1cZE1JXaogbXeLeY7WW-NUzDHmx8";
    int i=2;

    HomeWatcher mHomeWatcher;
    Dialog myDialog;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout relativeLayout;
    String st,ques_v;

    /*private boolean mB = false;
    private ClientService mSe;
    private ServiceConnection Sc =new ServiceConnection(){

        @Override


        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mSe = ((ClientService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mSe = null;
        }
    };*/



    @Override
    public void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.activity_home);


        //if(!Python.isStarted())
        //    Python.start(new AndroidPlatform(this));
        //Python python= Python.getInstance();
        //PyObject pyobj= python.getModule("duygu_tespiti");
        //PyObject obj= pyobj.call("treep","1");
         //myDialog = new Dialog(this);
        String[] emo= new String[]{"angry", "disgust", "fear", "mutlu", "sad", "surprise", "neutral"};
        Random rand = new Random();
         int r=rand.nextInt(6);
         Button invbut= (Button) findViewById(R.id.button4);
        if (!(emo[r].equals("surprise")||emo[r].equals("mutlu"))) {
            //
            /*relativeLayout= (RelativeLayout) findViewById(R.id.relative);
            showPopup(relativeLayout);*/
            invbut.setVisibility(View.VISIBLE);

        }
        /*else{
            invbut.setVisibility(View.INVISIBLE);
        }*/
        super.onCreate(savedInstanceState);
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
        /*doBindService1();
        Intent client = new Intent();
        client.setClass(this, ClientService.class);
        startService(client);
        try {
            mSe.ClientServ("192.168.43.103",22);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }*/


        //mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        //mSurfaceHolder = mSurfaceView.getHolder();
        //mSurfaceHolder.addCallback(this);
        //mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //Intent intent = new Intent(this, RecorderService.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startService(intent);
        //finish();
        ////Intent intent = new Intent(HomeActivity.this, RecorderService.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ////startService(intent);
        //finish();




        /*new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 stopService(new Intent(HomeActivity.this, RecorderService.class));


              }
         }, 6000);*/




        //Button btnStart = (Button) findViewById(R.id.StartService);
        /*btnStart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(CameraRecorder.this, RecorderService.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(intent);
                finish();
            }
        });

       // Button btnStop = (Button) findViewById(R.id.StopService);
        //btnStop.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                stopService(new Intent(CameraRecorder.this, RecorderService.class));
            }
        });*/









        Button quizz = findViewById(R.id.Quizz_mode);
        Button Learn = findViewById(R.id.Learn_mode);
        Button Fun = findViewById(R.id.Random_fun);
        Button python= findViewById(R.id.python);
       // mPlayer= (YouTubePlayerView) findViewById(R.id.suggested);
       // mPlayer.initialize(key, this);
       // mPlayer= (YouTubePlayerView)findViewById(R.id.suggested1);
       // mPlayer.initialize(key,this);
       // mPlayer= (YouTubePlayerView)findViewById(R.id.suggested2);
       // mPlayer.initialize(key,this);
       // mPlayer= (YouTubePlayerView)findViewById(R.id.suggested3);
       // mPlayer.initialize(key,this);






        quizz.setOnClickListener(this);
        Fun.setOnClickListener(this);
        Learn.setOnClickListener(this);
        python.setOnClickListener(this);
       // test.setOnClickListener(this);
        //drawerLayout= findViewById(R.id.nav_view);
        navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();



        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();



    }







    @SuppressLint("ResourceType")
    public void showPopup(View view){
        /*PopupMenu popup= new PopupMenu(this,view);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.popup_menu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        popup.show();*/
        mServ.pauseMusic();
        Intent intent= new Intent(this,RandomfunActivity.class);
        startActivity(intent);

    }
   // @Override
    public boolean onMenuClickItem(MenuItem item){
        if(item.getItemId() == R.id.question) {
            mServ.pauseMusic();
            Intent intent = new Intent(this, RandomfunActivity.class);
            startActivity(intent);
        }
        return true;
    }


    public void ShowPopup(View v) {

        Button btnFollow;
        myDialog.setContentView(R.layout.popup_content);


        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {




        if(!wasRestored){
            try {
                i++;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Quizz_mode :
                mServ.pauseMusic();

                Intent myIntent = new Intent(this, QuizzActivity.class);
                myIntent.putExtra("i", 0);
                startActivity(myIntent);
                break;
            case R.id.Learn_mode :
                mServ.pauseMusic();

                Intent intent1 = new Intent(HomeActivity.this, LearnActivity.class);
                intent1.putExtra("i",0);
                startActivity(intent1);


                break;
            /*case R.id.python:
                Intent intent3 = new Intent(HomeActivity.this, PythonActivity.class);
                //intent3.putExtra("i",0);
                startActivity(intent3);
                break;*/

            /*case R.id.Random_fun :
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(HomeActivity.this, FunActivity.class);
                        startActivity(intent);

                    }
                },1500);*/
                //break;
            case R.id.Random_fun:
                mServ.pauseMusic();
                Intent intent2= new Intent(this, RandomfunActivity.class);
                startActivity(intent2);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.quizz_mode:
                Intent intent= new Intent(HomeActivity.this, QuizzActivity.class);
                startActivity(intent);
                break;
            case R.id.learn:
                mServ.pauseMusic();
                Intent intent2= new Intent(HomeActivity.this, LearnActivity.class);
                startActivity(intent2);
                break;
            default:
                throw new IllegalStateException("unlknown choice"+ item.getItemId());
        }

        return true;
    }



    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        @Override


        public void onServiceConnected(ComponentName name, IBinder
                binder1) {
            mServ = ((MusicService.ServiceBinder)binder1).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };




    /*private ClientService Cl;
    private ServiceConnection Scon1 =new ServiceConnection(){

        @Override


        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            Cl = ((ClientService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            Cl = null;
        }
    };*/






    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
  /*  void doBindService1(){
        bindService(new Intent(this,ClientService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mB = true;
    }

    void doUnbindService1()
    {
        if(mB)
        {
            unbindService(Sc);
            mB = false;
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }


    @Override
    public void onPause() {//Mark
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
        /*doUnbindService1();
        Intent client = new Intent();
        client.setClass(this,ClientService.class);
        stopService(client);*/



    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}