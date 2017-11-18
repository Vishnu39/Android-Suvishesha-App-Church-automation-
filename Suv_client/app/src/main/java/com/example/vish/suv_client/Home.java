package com.example.vish.suv_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * Created by Vish on 7/26/2017.
 */

public class Home extends AppCompatActivity {
   // Constants S;
    Logout l;
    TextView t1;
   // Constants c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.home);
        Button bt=(Button) findViewById(R.id.button2);
        Button bt2=(Button) findViewById(R.id.button7);
        Button bt3=(Button) findViewById(R.id.button8);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toexam();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                tokalo();
            }
        });
        l=new Logout(this);
        //c=new ;
        t1=(TextView) findViewById(R.id.textView4);
        //t1.setText(c.username);
        t1.setText(Constants.username);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });

    }

    public void toexam(){
       // Intent intent =new Intent(this,Exam.class);
        Intent intent =new Intent(this,Examlist.class);
        startActivity(intent);
    }
    public void tokalo(){
        Intent intent=new Intent(this,Kalotsavamlist.class);
        startActivity(intent);
    }
}
