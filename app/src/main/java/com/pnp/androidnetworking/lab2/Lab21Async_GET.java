package com.pnp.androidnetworking.lab2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Lab21Async_GET extends AsyncTask<Void,Void,Void> {
    String path = Lab21Activity.SERVER_NAME;
    TextView tvResult;
    String strName, strMark;
    Context context;
    String kq = "";

    public Lab21Async_GET(Context context, TextView tvResult, String strName, String strMark) {
        this.tvResult = tvResult;
        this.strName = strName;
        this.strMark = strMark;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        path +="?name="+this.strName+"&mark="+this.strMark;
        try {
            URL url = new URL(path);//lay duong dan
            //tao bo dem de doc du lieu
            BufferedReader bufferedReader
                    =new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            //
            String line="";//bien luu tru du lieu
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);//dua dong doc duoc vao stringBuilder
            }
            kq=stringBuilder.toString();//chuyen ket qua thanh chuoi

        }
        catch (Exception e)
        {
            kq = e.getMessage();//neu loi xay ra
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        tvResult.setText(kq);
    }
}
