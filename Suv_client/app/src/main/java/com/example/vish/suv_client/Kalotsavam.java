package com.example.vish.suv_client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Vish on 7/27/2017.
 */

public class Kalotsavam extends AppCompatActivity {
    Logout l;
    Returnhome H;
    TextView t;
    EditText total_stud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalotsavam);
        Button bt=(Button) findViewById(R.id.button11);
        Button bt1=(Button) findViewById(R.id.button12);
        TextView t=(TextView) findViewById(R.id.textView9);
        total_stud = (EditText) findViewById(R.id.editText4);
        t.setText(Constants.username);
        l=new Logout(this);
        H=new Returnhome(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KaloDetails();
                H.Returnhome();
            }
        });

    }
    public void KaloDetails() {
        String url = "http://suviseshabhavan.com/suvi/android/client/addparticipants.php?cid="+Constants.cid+"&kid="+Constants.kid+"&ns="+total_stud.getText();
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

                Kalotsavam.this.runOnUiThread(new Runnable() {
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
