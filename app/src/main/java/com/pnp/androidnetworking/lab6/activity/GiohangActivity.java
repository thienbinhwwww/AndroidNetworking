package com.pnp.androidnetworking.lab6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.adapter.GiohangAdapter;
import com.pnp.androidnetworking.lab6.ultil.CheckConnetion;

public class GiohangActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    TextView txtThongbao;
    static TextView txtTongTien;
    Button btnThanhToan,btnTiepTucMuaHang;
    GiohangAdapter giohangAdapter;
    ////
    void Anhxa()
    {
        listView = findViewById(R.id.listviewgiohang);
        txtThongbao = findViewById(R.id.textviewthongbao);
        txtTongTien = findViewById(R.id.textviewtongtien);
        btnThanhToan = findViewById(R.id.buttonthanhtoangiohang);
        btnTiepTucMuaHang = findViewById(R.id.buttontieptucmuahang);
        toolbar = findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, Lab6MainActivity.manggiohang);
        listView.setAdapter(giohangAdapter);
    }
    void ActionToolbar()
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
    void UpdateTongTien()
    {
        long tongtien=0;
        for(int i = 0; i< Lab6MainActivity.manggiohang.size(); i++)
        {
            tongtien += Lab6MainActivity.manggiohang.get(i).getGiasp();//cong don tien
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien)+" VND");
    }
    void CLickItemListview()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(Lab6MainActivity.manggiohang.size()<1)
                {
                    txtThongbao.setVisibility(View.VISIBLE);
                }
                else
                {
                    Lab6MainActivity.manggiohang.remove(i);//xoa hang trong gio hang
                    giohangAdapter.notifyDataSetChanged();
                    //cap nhat lai tong tien
                    UpdateTongTien();
                    if(Lab6MainActivity.manggiohang.size()<0)
                    {
                        txtThongbao.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        txtThongbao.setVisibility(View.INVISIBLE);
                        giohangAdapter.notifyDataSetChanged();
                        UpdateTongTien();
                    }
                }

            }
        });
    }
    void ClickButton()
    {
        btnTiepTucMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Lab6MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Lab6MainActivity.manggiohang.size()>0)
                {
                    //Intent intent = new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    //startActivity(intent);
                }
                else
                {
                    CheckConnetion.ShowToastLong(getApplicationContext(),"Khong co hang trong gio");
                }

            }
        });
    }
    void  CheckData()
    {
        if(Lab6MainActivity.manggiohang.size()<=0)
        {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }
        else
        {
            giohangAdapter.notifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }
    ////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();//xu ly su kien
        CLickItemListview();//xu ly su kien
        ClickButton();//xu ly su kien khi click button
        UpdateTongTien();//cap nhat tong tien
        CheckData();//kiem tra gio hang
    }
}
