package com.example.vish.suv_admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.id;

public class Removeuser extends AppCompatActivity {
    EditText usernam;
    Spinner spinner1;
    int id[];
    boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.removeuser);
        final Button removeusr=(Button) findViewById(R.id.button35);
       /* usernam=(EditText) findViewById(R.id.editText6);*/
        spinner1=(Spinner) findViewById(R.id.spinner4);
        getUsers();

        removeusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    String center_usenam = usernam.getText().toString();

                removeuser(center_usenam);*/
            if(flag){
               String center_usename= spinner1.getSelectedItem().toString();
                removeuser(center_usename);}
                else{
                Toast.makeText(getApplicationContext(),"No User remove",Toast.LENGTH_LONG).show();
            }
            }
        });
    }
    void getUsers(){

        String url = "http://suviseshabhavan.com/suvi/android/getcenter_2.php";
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

                Removeuser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showUser(myResponse);
                        //  startMainpage();

                        //  txtString.setText(myResponse);
                    }
                });

            }
        });
    }
    void showUser(String user)
    {

        try {
            JSONObject obj=new JSONObject(user);
            JSONArray arr = new JSONArray(obj.get("user").toString());
            String[]  users=new String[arr.length()];
            id=new int[arr.length()];
            flag=true;
            List<String> userlist = new ArrayList<String>();



            // Creating adapter for spinner

            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj1=new JSONObject(arr.getString(i));

                userlist.add(obj1.getString("cname"));
                id[i]=Integer.parseInt(obj1.getString("cid"));
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userlist);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner1.setAdapter(dataAdapter);
            // Binding resources Array to ListAdapter



        } catch(Exception e){
            flag=false;
            e.printStackTrace();
        }

    }
    public void removeuser(String center_usenam){
        String url = "http://suviseshabhavan.com/suvi/android/removecenter.php?uname="+center_usenam;
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

                Removeuser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"User removed Successfully",Toast.LENGTH_LONG).show();
                        //  txtString.setText(myResponse);
                    }
                });

            }
        });

    }
}

