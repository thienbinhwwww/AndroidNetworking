package com.pnp.androidnetworking.lab2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Lab22_POST extends AsyncTask<Void,Void,Void> {
    String path=Lab22Activity.SERVER_NAME;
    Context context;
    String strCanh;
    TextView tvResult;
    ProgressDialog progressDialog;//theo doi qua trinh doc du lieu
    String strResult;

    public Lab22_POST(Context context, String strCanh, TextView tvResult) {
        this.context = context;
        this.strCanh = strCanh;
        this.tvResult = tvResult;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //1.Lay duong dan
            URL url = new URL(path);
            //2.xu ly tham so POST
            String param = "canh="+ URLEncoder.encode(strCanh,"utf-8");//ma hoa tham so
            //3.mo ket noi
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //set tham so POST
            urlConnection.setDoOutput(true);//co lay output
            urlConnection.setRequestMethod("POST");//xac dinh method post
            urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
            //xac dinh kieu thuoc tinh
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            //lay ve tham so
            PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
            printWriter.print(param);
            printWriter.close();
            //doc du lieu
            String line="";
            BufferedReader bufferedReader
                    =new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while ((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line);
            }
            strResult = stringBuilder.toString();
            urlConnection.disconnect();

        }
        catch (Exception e)
        {
            strResult = e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

    // quá trình đọc dữ liệu
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Dang tinh toan...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    // hoàn tất đọc dữ liệu
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
        tvResult.setText(strResult);
    }
}
