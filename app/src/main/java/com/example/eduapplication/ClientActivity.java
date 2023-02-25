package com.example.eduapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class ClientActivity extends AppCompatActivity {
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        t= (TextView) findViewById(R.id.textView5);
        new MyTask().execute();
        /*try {
            //ClientServ("192.168.43.103",5000);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
    public void onDestroy() {
        super.onDestroy();
    }

    /*private Socket socket;
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
        /*try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return s;
    }*/




    private class MyTask extends AsyncTask<Void, Void, Void> {
        String result;
        private Socket socket;
        private DataInputStream input = null;
        private DataOutputStream output = null;
        String address="192.168.43.103";
        int port=8825;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            try {

                        socket= new Socket(address, port);
                        //InputStream input = socket.getInputStream();
                        //OutputStream output = socket.getOutputStream();
                        input= new DataInputStream(socket.getInputStream());
                        output= new DataOutputStream(socket.getOutputStream());



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

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //File f=new File("src\\main\\python\\video.mp4");

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
            //return void;
        return null;}

            }
}