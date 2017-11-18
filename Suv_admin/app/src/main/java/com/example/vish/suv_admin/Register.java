package com.example.vish.suv_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    Logout l;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button bt=(Button) findViewById(R.id.button8);
        Button bt2=(Button) findViewById(R.id.button9);
        Button bt3=(Button) findViewById(R.id.button10);
        Button bt4=(Button) findViewById(R.id.button12);
        Button bt5=(Button) findViewById(R.id.button11);
        Button bt6=(Button) findViewById(R.id.button5);
        Button bt7=(Button) findViewById(R.id.button14);
       // Button bt7=(Button) findViewById(R.id.button14);
        l=new Logout(this);
        t1=(TextView) findViewById(R.id.textView5);
        t1.setText(Appconstants.username);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exam();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seminar();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kalo();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                usrmngt();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeredkalo();
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeredexmstud();
            }
        });

    }
    public  void registeredkalo(){
        Intent intent =new Intent(this,Viewkalo.class);
        startActivity(intent);
    }
    public  void registeredexmstud(){
        Intent intent =new Intent(this,Viewexam.class);
        startActivity(intent);
    }
    public void exam(){
        Intent intent =new Intent(this,Exam.class);
        startActivity(intent);
    }
    public void seminar(){
        Intent intent=new Intent(this,Seminar.class);
        startActivity(intent);
    }
    public void kalo(){
        Intent intent=new Intent(this,Kalotsavam.class);
        startActivity(intent);
    }
    public void usrmngt(){
        Intent intent=new Intent(this,Usermngmnt.class);
        startActivity(intent);
    }
}

