package com.pnp.androidnetworking.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lab3MainActivity extends AppCompatActivity {
    EditText txtName,txtPrice, txtDes;
    Button btnInsert, btnGet;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_main);
        txtName = findViewById(R.id.edt_lab3MAc_name);
        txtPrice = findViewById(R.id.edt_lab3MAc_price);
        txtDes = findViewById(R.id.edt_lab3MAc_des);
        btnGet = findViewById(R.id.btn_lab3MAc_GetData);
        btnInsert = findViewById(R.id.btn_lab3MAc_Insert);
        tvResult = findViewById(R.id.tv_lab3_Result);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    ///insert
    private void insertData()//insert voi POST
    {
        //tao doi tuong chua du lieu
        Prd prd = new Prd();
        //dua du lieu nhap tu ban phim vao doi tuong
        prd.setName(txtName.getText().toString());
        prd.setPrice(txtPrice.getText().toString());
        prd.setDescription(txtDes.getText().toString());
        //1. Tao doi tong retro
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newwindedu.000webhostapp.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2.1 goi interface
        InterfaceInsertPrd interfaceInsertPrd=retrofit.create(InterfaceInsertPrd.class);
        //2.2 chuan bi ham
        Call<SvrResponsePrd> call
                =interfaceInsertPrd.insertPrd(prd.getName(),prd.getPrice(),prd.getDescription());
        //3. thuc thi ham
        call.enqueue(new Callback<SvrResponsePrd>() {
            //khi thanh cong
            @Override
            public void onResponse(Call<SvrResponsePrd> call, Response<SvrResponsePrd> response) {
                //tra ve ket qua
                SvrResponsePrd svrResponsePrd = response.body();
                tvResult.setText(svrResponsePrd.getMessage());
            }
            //khi that bai
            @Override
            public void onFailure(Call<SvrResponsePrd> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });

    }

    ////
    String strkq="";
    List<Products> ls;
    public void getData()//lay du lieu tu server bang Retrofit
    {
        //1. Tao doi tuong Retro
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://batdongsanabc.000webhostapp.com/mob403lab4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2. lay request
        InterfaceGetall interfaceGetall
                =retrofit.create(InterfaceGetall.class);
        Call<SvrResponseProducts> call = interfaceGetall.GetJSON();
        //3. Thuc thi request
        call.enqueue(new Callback<SvrResponseProducts>() {
            //neu thanh cong
            @Override
            public void onResponse(Call<SvrResponseProducts> call, Response<SvrResponseProducts> response) {
                //lay ket qua do server tra ve
                SvrResponseProducts svrResponseProducts=response.body();
                //chuyen ket qua sang list
                ls = new ArrayList<>(Arrays.asList(svrResponseProducts.getProducts()));
                //chuyen kq sang chuoi (cho de hien thi)
                for(Products p: ls)
                {
                    strkq+="Name: "+p.getName()+" - Price: "+p.getPrice()+"\n\n";
                }
                //dan ket qua len client
                tvResult.setText(strkq);
            }
            //neu that bai
            @Override
            public void onFailure(Call<SvrResponseProducts> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}