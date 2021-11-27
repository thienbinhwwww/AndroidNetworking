package com.pnp.androidnetworking.lab1;
// Sử dụng Thread để load dữ liệu

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

import java.net.URL;

public class Lab13Activity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab13);
        imageView = findViewById(R.id.img_demo13Ac);
        textView = findViewById(R.id.tv_demo13);
        button = findViewById(R.id.btn_demo13Ac);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //goi
                        final Bitmap bitmap = loadImage("http://i64.tinypic.com/28vaq8k.png");
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                textView.setText("Bai 13 load anh thanh cong");
                            }
                        });

                    }
                });
                thread.start();
            }
        });
    }
    private Bitmap loadImage(String str)
    {
        Bitmap bitmap = null;
        URL url;
        try {
            url = new URL(str);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
}
