package com.example.vish.suv_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Logout l;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button bt = (Button) findViewById(R.id.button2);
        Button bt2 = (Button) findViewById(R.id.button3);
        Button bt3 = (Button) findViewById(R.id.button4);
        // Button bt4=(Button) findViewById(R.id.button5);
        Button bt5 = (Button) findViewById(R.id.button6);
        Button bt6 = (Button) findViewById(R.id.button7);
        l = new Logout(this);
        t1 = (TextView) findViewById(R.id.textView4);
        t1.setText(Appconstants.username);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                notification();
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ebook();
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });

    }
    public void register(){
        Intent intent =new Intent(this,Register.class);
        startActivity(intent);
    }
    public void results(){
        Intent intent=new Intent(this,Ebookupload.class);
        startActivity(intent);
    }
    public void notification(){
        Intent intent=new Intent(this,Notifications.class);
        startActivity(intent);
    }
    public void ebook(){
        Intent intent=new Intent(this,Ebookupload.class);
        startActivity(intent);
    }
}
