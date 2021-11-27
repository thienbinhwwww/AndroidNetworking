package com.pnp.androidnetworking.lab1;
// Sử dung lớp nội với Async

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnp.androidnetworking.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Lab12Activity extends AppCompatActivity {
    Button button;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab12);

        textView =findViewById(R.id.tv_demo12);
        imageView = findViewById(R.id.img_demo12Ac);
        button = findViewById(R.id.btn_demo12Ac);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncLopNoi().execute("http://i64.tinypic.com/28vaq8k.png");
            }
        });
    }

    // Định nghĩa lớp nội
    public class  AsyncLopNoi extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null)
            {
                imageView.setImageBitmap(bitmap);
                textView.setText("Doc du lieu thanh cong bai 21");
            }
            else
            {
                textView.setText("Co loi khi load du lieu");
            }
        }
    }
}