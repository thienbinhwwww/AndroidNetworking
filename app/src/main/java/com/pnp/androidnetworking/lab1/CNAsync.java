package com.pnp.androidnetworking.lab1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

public class CNAsync extends AsyncTask<String, Void, Bitmap> {
    private Lab11Interface lab11Interface;

    public CNAsync(Lab11Interface lab11Interface, Context context) {
        this.lab11Interface = lab11Interface;
    }

    // hàm input
    @Override
    protected Bitmap doInBackground(String... strings) {
        try {
            // Lấy về
            return BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // hàm output
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (bitmap!=null){
            lab11Interface.onLoadBitmap(bitmap);// trả kết quả thành công
        }else {
            lab11Interface.onError();// thông báo lỗi
        }
    }

    // quá trình
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
