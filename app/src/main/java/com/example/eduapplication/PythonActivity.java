package com.example.eduapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/*
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class PythonActivity extends AppCompatActivity {
    EditText e;
    Button s;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);

         e= findViewById(R.id.meg);
         t= findViewById((R.id.sent));
        s= findViewById(R.id.send);
        if (!Python.isStarted()){
            Python.start(new AndroidPlatform(this));


        }



        Python py = Python.getInstance();
         final PyObject objpy= py.getModule("client1");
       // while(true) {


        s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PyObject obj = objpy.callAttr("chat", e.getText().toString());
                    t.setText(obj.toString());
                    //PyObject ob= objpy.callAttr("hear",1);
                    //while((ob== null)){
                    //    PyObject ob1= objpy.callAttr("hear");
                     //   if(ob1!=null){
                     //       s.setText("Working here");
                     //       ob=ob1;
                     //       break;
                      //  }









                }
            });



        //}



    }
}

 */
