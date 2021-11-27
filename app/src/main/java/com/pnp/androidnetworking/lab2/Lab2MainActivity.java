package com.pnp.androidnetworking.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab1.Lab1MainActivity;

public class Lab2MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_main);
    }

    public void Lab21(View view){
        Intent intent = new Intent(this, Lab21Activity.class);
        startActivity(intent);
    }
    public void Lab22(View view){
        Intent intent = new Intent(this, Lab22Activity.class);
        startActivity(intent);
    }
}