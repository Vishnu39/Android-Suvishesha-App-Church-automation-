package com.example.vish.suv_admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class MainActivity extends AppCompatActivity {
    EditText username, passwrd;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isConnectingToInternet(MainActivity.this))
        {
            Toast.makeText(getApplicationContext(),"Internet is available",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Internet is not available",Toast.LENGTH_LONG).show();

        }

        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        username = (EditText) findViewById(R.id.editText);
        passwrd = (EditText) findViewById(R.id.editText2);
        t1 = (TextView) findViewById(R.id.textView3);
        bt.setOnClickListener(new View.OnClickListener() {
            int numClicks = 0;

            @Override
            public void onClick(View v) {

                String usenam = username.getText().toString();
                String pass = passwrd.getText().toString();
              if(validation() ) {
                  login(usenam, pass);
              }
                /*if (usenam.compareTo("admin") == 0 && pass.compareTo("ad") == 0) {
                    setUsername();
                    startMainpage();
                } else {
                    t1.setText("Invalid username or password");
                }*/
            }
        });
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
    public void setUsername() {
        Appconstants.username = username.getText().toString();
    }


    public void startMainpage() {
        Intent intent = new Intent(this, Home.class);

        startActivity(intent);
    }

    public void login(String name,String pass) {
      //  String url = "http://192.168.43.37/Suvi/suvi/android/androidadminlogin.php?uname="+name+"&pswd="+pass;
        String url = "http://suviseshabhavan.com/suvi/android/androidadminlogin.php?uname="+name+"&pswd="+pass;
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

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                   if(myResponse.compareTo("1")==0)  {
                       setUsername();
                       startMainpage();
                   }
                     else{
                       t1.setText("Invalid username or password");
                   }
                      //  txtString.setText(myResponse);
                    }
                });

            }
        });





}


public boolean validation(){
    if(username.getText().toString().compareTo("")==0){
        t1.setText("Cannot be null");
        return  false;
    }
//    if ((username.getText().toString().matches("[a-zA-Z][0-9]+"))) {
//        t1.setText("must be alphanumeric");
//        return false;
//    }
    if(username.getText().toString().contains(" ")){
        t1.setText("Space not allowed in username");
        return  false;
    }
    if(username.getText().toString().substring(0, 1).matches("[0-9]")){
        t1.setText("First character must be alphabet");
        return  false;
    }

    /*if(passwrd.getText().toString().length()<8){
        t1.setText("Password length less than 8 are not allowed");
        return  false;
    }*/
    if(passwrd.getText().toString().compareTo("")==0){
        t1.setText("Cannot be null");
        return  false;
    }
    if(passwrd.getText().toString().contains(" ")){
        t1.setText("Space not allowed in password");
        return  false;
    }

    return  true;

}
/*public boolean alphanumeric()
    {
        while (username.getText().toString().matches("[a-zA-Z][0-9]+")){
            // t1.setText("Ccontains alphanumeric only");
            return  true;

        }

        t1.setText("Only alpha numeric characters allowed");
        return false;
}*/
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
}}