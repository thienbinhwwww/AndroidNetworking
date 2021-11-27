package com.pnp.androidnetworking.lab1;

// Sử dung interface với Async

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

public class Lab11Activity extends AppCompatActivity implements
        Lab11Interface,
        View.OnClickListener{
    Button button;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab11);
        textView =findViewById(R.id.tv_demo11);
        imageView = findViewById(R.id.img_demo11Ac);
        button = findViewById(R.id.btn_demo11Ac);
        button.setOnClickListener(this);
    }
    //khi click vao button
    @Override
    public void onClick(View view) {
        new CNAsync(this,this).execute("http://i64.tinypic.com/28vaq8k.png");
    }
    //tra ve ket qua
    @Override
    public void onLoadBitmap(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        textView.setText("Load du lieu thanh cong");
    }
    //neu co loi
    @Override
    public void onError() {
        textView.setText("Loi load du lieu");
    }
}