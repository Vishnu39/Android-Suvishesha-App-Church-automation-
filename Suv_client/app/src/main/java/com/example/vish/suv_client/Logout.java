package com.example.vish.suv_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vish on 7/26/2017.
 */

public class Logout {
    AppCompatActivity activity;
    Logout(AppCompatActivity activity)
    {
        this.activity=activity;
    }
    public void Logout(){
        Constants.username="";
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}
