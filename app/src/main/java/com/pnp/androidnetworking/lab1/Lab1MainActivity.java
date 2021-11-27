package com.pnp.androidnetworking.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pnp.androidnetworking.R;

public class Lab1MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1_main);
    }

    public void Lab11(View view){
        Intent intent = new Intent(this, Lab11Activity.class);
        startActivity(intent);
    }
    public void Lab12(View view){
        Intent intent = new Intent(this, Lab12Activity.class);
        startActivity(intent);
    }
    public void Lab13(View view){
        Intent intent = new Intent(this, Lab13Activity.class);
        startActivity(intent);
    }
}