package com.example.vish.suv_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Notifications extends AppCompatActivity {
    Logout l;
    TextView t1,t2;
    EditText mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        Button bt = (Button) findViewById(R.id.button29);
        l = new Logout(this);
        t1 = (TextView) findViewById(R.id.textView23);
        t2 = (TextView) findViewById(R.id.textView20);
        t1.setText(Appconstants.username);
        mess = (EditText) findViewById(R.id.editText9);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l.Logout();
            }
        });

        Button bt1 = (Button) findViewById(R.id.button28);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    notification(mess.getText().toString());
                }
            }
        });
    }
    public boolean validation() {
        if (mess.getText().toString().compareTo("") == 0) {
            t2.setText("Notification Cannot be blank");
            return false;
        }
        return true;
    }
    public void notification(String mess) {
        String url = "http://suviseshabhavan.com/suvi/android/addnotification.php?noti="+mess;
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

                Notifications.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Notification added Successfully",Toast.LENGTH_LONG).show();
                        //  txtString.setText(myResponse);
                        t2.setText("POSTED");
                    }
                });

            }
        });





    }
}
