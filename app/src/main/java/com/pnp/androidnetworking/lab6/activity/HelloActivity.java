package com.pnp.androidnetworking.lab6.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.ultil.CheckConnetion;

public class HelloActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        //tao doi tuong timer
        Timer timer = new Timer();
        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))//neu co mang
        {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(HelloActivity.this, Lab6MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2000);
        }
        else
        {
            CheckConnetion.ShowToastLong(getApplicationContext(),"Kiem tra lai mang");
            finish();
        }
    }
}
