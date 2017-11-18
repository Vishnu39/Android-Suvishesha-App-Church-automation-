package com.example.vish.suv_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Vish on 8/2/2017.
 */

public class Logout {
    AppCompatActivity activity;
    Logout(AppCompatActivity activity)
    {
        this.activity=activity;
    }
    public void Logout(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}