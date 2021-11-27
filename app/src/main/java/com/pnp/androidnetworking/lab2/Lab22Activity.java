package com.pnp.androidnetworking.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

public class Lab22Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String SERVER_NAME = "https://newwindedu.000webhostapp.com/API/lab22_api_post.php";
    TextView tvResult;
    EditText txtCanh;
    Button button;
    String strCanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab22);
        tvResult = findViewById(R.id.tv_lab22);
        txtCanh = findViewById(R.id.edt_lab22_canh);
        button = findViewById(R.id.btn_lab22);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        strCanh = txtCanh.getText().toString();
        Lab22_POST d = new Lab22_POST(this,strCanh,tvResult);
        d.execute();
    }


}