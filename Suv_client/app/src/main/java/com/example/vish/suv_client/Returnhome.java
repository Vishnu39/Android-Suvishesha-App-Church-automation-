package com.example.vish.suv_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vish on 10/5/2017.
 */

public class Returnhome {
    AppCompatActivity activity;
    Returnhome(AppCompatActivity activity)
    {
        this.activity=activity;
    }
    public void Returnhome(){
        Intent intent = new Intent(activity, Home.class);
        activity.startActivity(intent);
    }
}
