package com.example.eduapplication;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.*;
import java.net.*;

public class ClientService extends Service {

    private final IBinder mBinder1 = (IBinder) new ClientService.ServiceBinder();
    MediaPlayer mPlayer;
    private int length = 0;
    private Intent intent;

    public ClientService() {
    }

    public class ServiceBinder extends Binder {
        ClientService getService() {
            return ClientService.this;
        }
    }

    /**
     * @param arg0
     * @return
     */
    @Override
    public IBinder onBind(Intent arg0) {
        intent = arg0;
        return mBinder1;
    }





    String st="";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       /* try {
            //st=ClientServ("192.168.43.103", 22);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    public void ClientServ(String address, int port) throws IOException {
        try {
            socket= new Socket(address, port);
            //InputStream input = socket.getInputStream();
            //OutputStream output = socket.getOutputStream();
            input= new DataInputStream(socket.getInputStream());
            output= new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //File f=new File("src\\main\\python\\video.mp4");
        String f= "Hi how are you";
        byte[] buffer= new byte[1024];
        byte[] line= new byte[1024];
        ByteArrayOutputStream os= new ByteArrayOutputStream();
        FileInputStream fls= new FileInputStream(f);
        int read;
        while ((read= fls.read(buffer)) !=-1){
            try{
                //line= ();
                output.write(buffer,0,read);



                //line= new String(input.read)
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /*String s= "";
        while((input.read(line,0,1024))!=-1){
            s=s+new String(line);


        }*/
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return s;
    }



   /* public static void main(String args[]) throws IOException {
        ClientService client= new ClientService("192.168.43.103", 22);
    }*/

    /*@Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }*/
}
