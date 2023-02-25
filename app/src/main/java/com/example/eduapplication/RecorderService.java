package com.example.eduapplication;

import java.io.IOException;
import java.util.List;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import static com.example.eduapplication.HomeActivity.mCamera;

public class RecorderService extends Service {








    private boolean mIsBound=false;
    private ClientService Cl;
    private ServiceConnection Scon1 =new ServiceConnection(){

        @Override


        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            Cl = ((ClientService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            Cl = null;
        }
    };
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       /* try {
            //st=ClientServ("192.168.43.103", 22);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        onCreate();
       /* mRecordingStatus = false;
        //mServiceCamera = HomeActivity.mCamera;
        mServiceCamera = Camera.open(1);
        //mSurfaceView = CameraRecorder.mSurfaceView;
        //mSurfaceHolder = CameraRecorder.mSurfaceHolder;

        //super.onCreate();
        if (mRecordingStatus == false)
            startRecording();*/

        //startService(new Intent(this, ClientService.class));

        return START_STICKY;
    }
    /*void doBindService(){
        bindService(new Intent(this,ClientService.class),
                Scon1, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon1);
            mIsBound = false;
        }
    }*/





    private static final String TAG = "RecorderService";
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private static Camera mServiceCamera;
    private boolean mRecordingStatus;
    private MediaRecorder mMediaRecorder;

    @Override
    public void onCreate() {
        mRecordingStatus = false;
        //mServiceCamera = HomeActivity.mCamera;
        mServiceCamera = Camera.open(1);
        //mSurfaceView = CameraRecorder.mSurfaceView;
        //mSurfaceHolder = CameraRecorder.mSurfaceHolder;
        doBindService();

        super.onCreate();
        if (mRecordingStatus == false)
            startRecording();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        stopRecording();
        mRecordingStatus = false;
        doUnbindService();

        super.onDestroy();
    }

    void doBindService(){
        bindService(new Intent(this,ClientService.class),
                Scon1, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon1);
            mIsBound = false;
        }
    }


    String s;

    public boolean startRecording(){
        try {
            //Toast.makeText(getBaseContext(), "Recording Started", Toast.LENGTH_SHORT).show();

            //mServiceCamera = Camera.open();
            Camera.Parameters params = mServiceCamera.getParameters();
            mServiceCamera.setParameters(params);
            Camera.Parameters p = mServiceCamera.getParameters();



            mServiceCamera.unlock();

            mMediaRecorder = new MediaRecorder();
            //mMediaRecorder.setCamera(mServiceCamera);
            //mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
            mMediaRecorder.setOutputFile("src\\main\\python\\video.mp4");
            //startService(new Intent(this, ClientService.class));
             //s= Cl.ClientServ("192.168.43.103",8825);
            //stopService(new Intent(this, ClientService.class));

            //mMediaRecorder.setVideoFrameRate(30);
            //mMediaRecorder.setVideoSize(mPreviewSize.width, mPreviewSize.height);
            //mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());


            mMediaRecorder.prepare();
            mMediaRecorder.start();

            mRecordingStatus = true;

            return true;
        } catch (IllegalStateException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void stopRecording() {
        //Toast.makeText(getBaseContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
        try {
            mServiceCamera.reconnect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mMediaRecorder.stop();
        mMediaRecorder.reset();

        //mServiceCamera.stopPreview();
        mMediaRecorder.release();

        mServiceCamera.release();
        mServiceCamera = null;

    }
}
