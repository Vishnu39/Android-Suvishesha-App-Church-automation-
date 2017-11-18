package com.example.vish.suv_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Exam extends AppCompatActivity {
    Logout l;
    TextView t1;
    Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam);
        Button bt=(Button) findViewById(R.id.button15);
        l=new Logout(this);
        t1=(TextView) findViewById(R.id.textView8);
        t1.setText(Appconstants.username);

        spinner1=(Spinner) findViewById(R.id.spinner);
        spinner2=(Spinner) findViewById(R.id.spinner2);
        Button bt1=(Button) findViewById(R.id.button13);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExam(spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString());
            }
        });
    }
    public void addExam(String term,String clas) {
        String url = "http://suviseshabhavan.com/suvi/android/addexam.php?terminal="+term+"&class="+clas;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

if(myResponse.substring(0,1).compareTo("1")==0) {
    Exam.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getApplicationContext(), "Exam added Successfully", Toast.LENGTH_LONG).show();
            //  Toast.makeText(getApplicationContext(),"Exam added Successfully",Toast.LENGTH_LONG).show();
            //  txtString.setText(myResponse);
        }
    });
}
else{
    Exam.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getApplicationContext(), "Exam already opened", Toast.LENGTH_LONG).show();
            //  Toast.makeText(getApplicationContext(),"Exam added Successfully",Toast.LENGTH_LONG).show();
            //  txtString.setText(myResponse);
        }
    });
}
            }
        });





    }
}
