package com.pnp.androidnetworking.lab6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.adapter.DienthoaiAdapter;
import com.pnp.androidnetworking.lab6.model.Sanpham;
import com.pnp.androidnetworking.lab6.ultil.CheckConnetion;
import com.pnp.androidnetworking.lab6.ultil.server;

public class DienThoaiActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    int page=1;
    int idDienThoai = 0;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Sanpham> mangdienthoai;
    ////dinh nghia cac ham
    void Anhxa()
    {
        toolbar = findViewById(R.id.toolbardienthoai);
        listView = findViewById(R.id.listviewdienthoai);
        mangdienthoai = new ArrayList<>();
        dienthoaiAdapter= new DienthoaiAdapter(getApplicationContext(),mangdienthoai);
        listView.setAdapter(dienthoaiAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangdienthoai.get(i));
                startActivity(intent);
            }
        });


    }
    void GetIDLoaiSanPham()
    {
        idDienThoai = getIntent().getIntExtra("idloaisanpham",-1);
    }
    void ActionToolBarDienThoai()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    void GetDataDienThoai(int p)
    {


        //1. khai bao thu vien volley
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //2. Tao request
        //StringRequest(phuongthuc,DuongDan,ThanhCong,ThatBai)
        String path = server.Duongdandienthoai+String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String Tendt="";
                int Giadt=0;
                String Hinhanhdt="";
                String Motadt="";
                int idsp=0;
                if(response!=null)
                {
                    try {
                        JSONArray jsonArray = new JSONArray(response);//lay ve ket qua dang mang
                        for(int i=0;i<jsonArray.length();i++)//doc cac thanh phan cua mang
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);//lay doi tuong thu i
                            id=jsonObject.getInt("id");
                            Tendt = jsonObject.getString("tensp");
                            Giadt = jsonObject.getInt("giasp");
                            Hinhanhdt = jsonObject.getString("hinhanhsp");
                            Motadt = jsonObject.getString("motasp");
                            idsp = jsonObject.getInt("idsanpham");
                            //dua vao array
                            mangdienthoai.add(new Sanpham(id,Tendt,Giadt,Hinhanhdt,Motadt,idsp));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    CheckConnetion.ShowToastLong(getApplicationContext(),"Loi mang");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Loi doc du lieu",error.getMessage());
            }
        }){
            //truyen tham so post
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("idsanpham",String.valueOf(idDienThoai));
                return param;
            }
        };
        //3. Thuc thi request
        requestQueue.add(stringRequest);
    }
    //dinh nghia cac ham
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
        {
            //lay ve id loai san pham de load dien thoai
            GetIDLoaiSanPham();
            //cho phep xu ly su kien tren Loai San pham (Action bar)
            ActionToolBarDienThoai();
            //lay ve Dien thoai
            GetDataDienThoai(page);
        }
        else
        {
            CheckConnetion.ShowToastLong(getApplicationContext(),"Kiem tra lai mang");
            finish();
        }
    }
}
