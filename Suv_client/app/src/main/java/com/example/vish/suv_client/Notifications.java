package com.example.vish.suv_client;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Notifications extends ListActivity {
    int ids[];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // storing string resources into Array

        getUsers();
    }


    void getUsers(){

        String url = "http://suviseshabhavan.com/suvi/android/client/notificationlist.php";
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
            JSONArray arr = new JSONArray(obj.get("notfication").toString());
            String[]  users=new String[arr.length()];
            ids=new int[arr.length()];
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj1=new JSONObject(arr.getString(i));

                ids[i]=Integer.parseInt(obj1.getString("nid"));

                users[i]=obj1.getString("mess");

            }


            // Binding resources Array to ListAdapter
            this.setListAdapter(new ArrayAdapter<String>(this, R.layout.notificationlist, R.id.label, users));


        } catch(Exception e){
            e.printStackTrace();
        }
        ListView lv = getListView();
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String product = ((TextView) view).getText().toString();
                Constants.examid=ids[position];
                // Launching new Activity on selecting single List Item
              //  Intent i = new Intent(getApplicationContext(), Exam.class);
                // sending data to new activity
                // i.putExtra("product", product);
               // startActivity(i);

            }
        });
    }
}