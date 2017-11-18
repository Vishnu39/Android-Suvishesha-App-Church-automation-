package com.example.vish.suv_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Usermngmnt extends AppCompatActivity {
    Logout l;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermngmnt);
        Button bt=(Button) findViewById(R.id.button25);
        Button bt2=(Button) findViewById(R.id.button22);
        Button bt3=(Button) findViewById(R.id.button23);
        Button bt4=(Button) findViewById(R.id.button24);
        l=new Logout(this);
        t1=(TextView) findViewById(R.id.textView18);
        t1.setText(Appconstants.username);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addwindow();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removewindow();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlist();
            }
        });

    }
    public void addwindow(){
        Intent intent=new Intent(this,Adduser.class);
        startActivity(intent);
    }
    public void  removewindow(){
        Intent intent=new Intent(this,Removeuser.class);
        startActivity(intent);
    }
    public void  userlist(){
        Intent intent=new Intent(this,Userlist.class);
        startActivity(intent);
    }


}
