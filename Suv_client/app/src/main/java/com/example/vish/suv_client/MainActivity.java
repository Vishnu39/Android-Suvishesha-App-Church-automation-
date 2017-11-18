package com.example.vish.suv_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=(Button) findViewById(R.id.button);
       // Button bt2=(Button) findViewById(R.id.button3);
        Button bt3=(Button) findViewById(R.id.button4);
        Button bt4=(Button) findViewById(R.id.button5);
        Button bt5=(Button) findViewById(R.id.button13);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toseminarlist();
            }
        });
       /* bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toresults();
            }
        });*/
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tonotifications();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toebooks();
            }
        });

        bt.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public void onClick(View v) {
     tologin();
    }
    public void tologin(){
        Intent intent =new Intent(this,Login.class);
        startActivity(intent);

    }
    public void toresults(){
        Intent intent =new Intent(this,Resultlist.class);
        startActivity(intent);
    }
    public void tonotifications(){
        Intent intent=new Intent(this,Notifications.class);
        startActivity(intent);
    }
    public void toebooks() {
        Intent intent=new Intent(this,Ebooklist.class);
        startActivity(intent);
    }
    public void toseminarlist(){
        // Intent intent =new Intent(this,Exam.class);
        Intent intent =new Intent(this,Seminarlist.class);
        startActivity(intent);
    }
}
