package com.example.vish.suv_client;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class Login extends AppCompatActivity {
    EditText inputusername,inputPassword;
    TextView t1;
   // Constants c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isConnectingToInternet(
                Login.this))
        {
            Toast.makeText(getApplicationContext(),"internet is available",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"internet is not available",Toast.LENGTH_LONG).show();

        }
        setContentView(R.layout.login);
        Button bt=(Button)findViewById(R.id.button6);
        inputusername = (EditText) findViewById(R.id.editText);
        inputPassword = (EditText) findViewById(R.id.editText3);
        t1=(TextView) findViewById(R.id.textView3);
        bt.setOnClickListener(new View.OnClickListener() {
            // boolean clicked = false;
            int numClicks = 0;

            @Override
            public void onClick(View v) {

                String usename =inputusername.getText().toString();
                String pass=inputPassword.getText().toString();
                if(inputusername.getText().toString().compareTo("")==0){
                    t1.setText("Username cannot be null");
                    return;
                }
                if(inputPassword.getText().toString().compareTo("")==0){
                    t1.setText("Password cannot be null");
                    return;
                }
                else {
                    login(usename, pass);
                }
                /*if(usename.compareTo("center")==0 && pass.compareTo("123")==0) {
                    setUsername();
                    tohome();

                }
                else{
                    t1.setText("Invalid username or password");
                }*/
            }
        });
    }
    public void setUsername(){
        Constants.username=inputusername.getText().toString();
    }
    public void tohome(){
        Intent intent =new Intent(this,Home.class);
        startActivity(intent);

    }
    public void login(String name,String pass) {
        String url = "http://suviseshabhavan.com/suvi/android/androidcenterlogin.php?uname="+name+"&pswd="+pass;
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


                Login.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(myResponse.compareTo("0")==0)  {
                            t1.setText("Invalid username or password");
                        }
                        else{

                            Constants.cid=Integer.parseInt(myResponse);
                            setUsername();
                            tohome();
                        }
                        //  txtString.setText(myResponse);
                    }
                });

            }
        });





    }
    public static boolean isConnectingToInternet(Context context)
    {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
        }
        return false;
    }


}