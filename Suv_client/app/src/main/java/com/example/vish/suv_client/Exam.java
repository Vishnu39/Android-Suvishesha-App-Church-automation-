package com.example.vish.suv_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Vish on 7/27/2017.
 */

public class Exam extends AppCompatActivity {
    Logout L;
    TextView t1;
    Spinner s,s2;
    EditText total_stud;
    Button bt1;
    Returnhome H;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam);
        Button bt=(Button) findViewById(R.id.button9);
        TextView t1=(TextView) findViewById(R.id.textView5);
        //Spinner s
        t1.setText(Constants.username);
        total_stud = (EditText) findViewById(R.id.editText2);
        L=new Logout(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.Logout();
            }
        });
        Button bt1=(Button) findViewById(R.id.button10);
        H=new Returnhome(this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examDetails();
                H.Returnhome();
            }
        });
    }


    public void examDetails() {
        String url = "http://suviseshabhavan.com/suvi/android/client/addstudents.php?cid="+Constants.cid+"&examid="+Constants.examid+"&ns="+total_stud.getText();
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

                Exam.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Students Registerd Successfully",Toast.LENGTH_LONG).show();
                        //  txtString.setText(myResponse);
                    }
                });

            }
        });





    }
}
