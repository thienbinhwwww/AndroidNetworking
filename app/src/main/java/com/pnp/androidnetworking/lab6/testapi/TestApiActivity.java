package com.pnp.androidnetworking.lab6.testapi;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.pnp.androidnetworking.R;

public class TestApiActivity extends AppCompatActivity {
    Button btnI,btnU,btnS,btnD;
    TextView tvResult;
    public void executeVolley(Context context, TextView textView)
    {
        //1. Tao request
        RequestQueue queue = Volley.newRequestQueue(context);
        //2. url
        String url = "https://batdongsanabc.000webhostapp.com/w3demo/select.php";
        //StringRequest(mehod,url,thanhCong,ThatBai)
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText("Thanh cong: "+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Loi: "+error.getMessage());
            }
        });
        //3. thuc thi request
        queue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);
        btnD = findViewById(R.id.testAPIbtnDelete);
        btnU = findViewById(R.id.testAPIbtnUpdate);
        btnI = findViewById(R.id.testAPIbtnInsert);
        btnS = findViewById(R.id.testAPIbtnSelect);
        tvResult = findViewById(R.id.testApiTextview);
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //executeVolley(TestApiActivity.this,tvResult);
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeVolley(TestApiActivity.this,tvResult);
            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //executeVolley(TestApiActivity.this,tvResult);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //executeVolley(TestApiActivity.this,tvResult);
            }
        });
    }
}
