package com.pnp.androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pnp.androidnetworking.lab1.Lab1MainActivity;
import com.pnp.androidnetworking.lab2.Lab2MainActivity;
import com.pnp.androidnetworking.lab3.Lab3MainActivity;
import com.pnp.androidnetworking.lab4.lab41.lab41UpdateActivity;
import com.pnp.androidnetworking.lab5.Lab5MainActivity;
import com.pnp.androidnetworking.lab6.activity.Lab6MainActivity;
import com.pnp.androidnetworking.lab7.Lab7MainActivity;
import com.pnp.androidnetworking.lab8.Lab8MainActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Lab1(View view){
        Intent intent = new Intent(this, Lab1MainActivity.class);
        startActivity(intent);
    }
    public void Lab2(View view){
        Intent intent = new Intent(this, Lab2MainActivity.class);
        startActivity(intent);
    }
    public void Lab3(View view){
        Intent intent = new Intent(this, Lab3MainActivity.class);
        startActivity(intent);
    }
    public void Lab4(View view){
        Intent intent = new Intent(this, lab41UpdateActivity.class);
        startActivity(intent);
    }
    public void Lab5(View view){
        Intent intent = new Intent(this, Lab5MainActivity.class);
        startActivity(intent);
    }
    public void Lab6(View view){
        Intent intent = new Intent(this, Lab6MainActivity.class);
        startActivity(intent);
    }
    public void Lab7(View view){
        Intent intent = new Intent(this, Lab7MainActivity.class);
        startActivity(intent);
    }
    public void Lab8(View view){
        Intent intent = new Intent(this, Lab8MainActivity.class);
        startActivity(intent);

    }
    public void Assignment(View view){
        Intent intent = new Intent(this, Lab1MainActivity.class);
        startActivity(intent);
    }
}