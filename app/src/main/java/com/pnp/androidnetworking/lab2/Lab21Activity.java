package com.pnp.androidnetworking.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

public class Lab21Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME = "https://newwindedu.000webhostapp.com/API/api_get.php";
    EditText txtName,txtMark;
    Button btn;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab21);

        txtName = findViewById(R.id.edt_lab21_ten);
        txtMark = findViewById(R.id.edt_lab21_diem);
        btn = findViewById(R.id.btn_lab21);
        tvResult = findViewById(R.id.tv_lab21);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strName = txtName.getText().toString();
        String strMark = txtMark.getText().toString();
        Lab21Async_GET d = new Lab21Async_GET(this,tvResult,strName,strMark);
        d.execute();
    }
}