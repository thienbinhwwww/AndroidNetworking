package com.pnp.androidnetworking.lab6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.model.Giohang;
import com.pnp.androidnetworking.lab6.model.Sanpham;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgchitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btnmua;
     int id;
     String Tensanpham;
     Integer Giasanpham;
     String Hinhanhsanpham;
     String Motasanpham;
     int IDSanpham;
    void Anhxa()
    {
        toolbar = findViewById(R.id.toolbarchitietsanpham);
        imgchitiet = findViewById(R.id.imageviewchitietsanpham);
        txtten = findViewById(R.id.textviewtenchitietsanpham);
        txtgia = findViewById(R.id.textviewgiachitietsanpham);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        btnmua = findViewById(R.id.buttondatmua);
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
    void EventSpinner()
    {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    void GetInfomation()
    {
        //lay thong tin truyen sang
        Sanpham sanpham = (Sanpham)getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        Tensanpham = sanpham.getTensanpham();
        Giasanpham = sanpham.getGiasanpham();
        Hinhanhsanpham = sanpham.getHinhanhsanpham();
        Motasanpham = sanpham.getMotasanpham();
        IDSanpham = sanpham.getIDSanpham();
        //dua thong tin vao chi tiet
        txtten.setText(Tensanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Gia: "+decimalFormat.format(Giasanpham)+" VND");
        txtmota.setText(Motasanpham);
        //anhr
        Picasso.get().load(Hinhanhsanpham)
                .placeholder(R.drawable.home)
                .error((R.drawable.erro))
                .into(imgchitiet);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();//findbyviewId
        ActionToolbar();//xu ly su kien khi click vao dien thoai
        GetInfomation();//lay thong tin chuyen sang
        EventSpinner();//chon so luong
        EventButton();//them san pham

    }
    void  EventButton()//xu ly su kien click button
    {
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Lab6MainActivity.manggiohang.size()>0)//gio hang khong rong
                {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                    boolean tontaimahang = false;
                    for(int i = 0; i< Lab6MainActivity.manggiohang.size(); i++)//dem xem trong gio hang co gi
                    {
                        if(Lab6MainActivity.manggiohang.get(i).getIdsp()==id)//neu co hang ta muon mua them
                        {
                            //so luong = so luong cu + moi
                            Lab6MainActivity.manggiohang.get(i).setSoluongsp(Lab6MainActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if(Lab6MainActivity.manggiohang.get(i).getSoluongsp()>=10)//neu so luong sp >10, giu nguyen 10
                            {
                                Lab6MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            //tinh tien = Don gia * so luong
                            Lab6MainActivity.manggiohang.get(i).setGiasp(Giasanpham* Lab6MainActivity.manggiohang.get(i).getSoluongsp());
                            tontaimahang  =true;
                        }
                    }
                    if(tontaimahang==false)//truong hop co hang, nhung ma hang muon mua them khong ton tai
                    {
                        int sl1 = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                        //tinh tien
                        long Tien2 = sl1*Giasanpham;
                        //them vao mang gio hang
                        Lab6MainActivity.manggiohang.add(new Giohang(id,Tensanpham,Tien2,Hinhanhsanpham,sl1));
                    }
                }
                else //gio hang rong
                {
                    int sl2 = Integer.parseInt(spinner.getSelectedItem().toString());//lay so luong trong spinner
                    //tinh tien
                    long Tien2 = sl2*Giasanpham;
                    //them vao mang gio hang
                    Lab6MainActivity.manggiohang.add(new Giohang(id,Tensanpham,Tien2,Hinhanhsanpham,sl2));
                }
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            }
        });
    }
}
