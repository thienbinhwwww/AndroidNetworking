package com.pnp.androidnetworking.lab6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.adapter.LoaispAdapter;
import com.pnp.androidnetworking.lab6.model.Giohang;
import com.pnp.androidnetworking.lab6.model.Loaisp;
import com.pnp.androidnetworking.lab6.ultil.CheckConnetion;
import com.pnp.androidnetworking.lab6.ultil.server;


public class Lab6MainActivity extends AppCompatActivity {
    public static ArrayList<Giohang> manggiohang;
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp="";
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    //demo4----
    void ClickItemListview()//xu ly su kien
    {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(Lab6MainActivity.this, Lab6MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Kiem tra mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);//close menu trai
                        break;
                    case 1:
                        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(Lab6MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckConnetion.ShowToastLong(getApplicationContext(),"Kiem tra mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);//close menu trai
                        break;
                }
            }
        });
    }
    //demo4---
    //------
    void ViewFlip()
    {
        ArrayList<String> mang = new ArrayList<>();
        mang.add("https://fptshop.com.vn/Uploads/images/2015/SANPHAM/MinhtriBin/11_02_dell-vostro-v5568i5-7200u-25ghz/dell-vostro-v5568i5-7200u-25ghz1.jpg");
        mang.add("https://www.lenovo.com/medias/ww-lenovo-thinkpad-x1-carbon-2017-feature4.png?context=bWFzdGVyfHJvb3R8OTAwMzd8aW1hZ2UvcG5nfGhhYi9oMzgvOTM1NzAyODIyOTE1MC5wbmd8NmY4MTY3OWY0NGI2ZDkzOTQ0MWI4NTBiZTJkNjg1OWRjM2JhMjI4MDBmNWU2OWZkMTJmOWMzMWU4ZjI3MWQ3NA&w=1920");
        mang.add("https://www.lenovo.com/medias/ww-lenovo-laptop-thinkpad-x1-carbon5-gallery-13.png?context=bWFzdGVyfHJvb3R8MTAyMzg0fGltYWdlL3BuZ3xoY2IvaGRiLzkzNTcwMjU3Mzg3ODIucG5nfDE0N2I5OWFkODllYjNjZmE0YTJiMWY3OTlkMmVhMzkwMTdhYmVlZWRiZDAyMDAzNGJlMTlmYTY0NGE0ZmNlNDY");
        mang.add("https://store.storeimages.cdn-apple.com/4981/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone/xs/iphone-xs-max-gold-select-2018?wid=1200&hei=630&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1535655075417");

        for(int i=0;i<mang.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mang.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);

        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewlipper);
    }
        void Anhxa()
        {


            if(manggiohang!=null)
            {

            }
            else
            {
                manggiohang = new ArrayList<>();
            }
            toolbar = (Toolbar)findViewById(R.id.toolbarmanhinhchinh);
            viewFlipper = (ViewFlipper)findViewById(R.id.viewlipper);

            navigationView = (NavigationView)findViewById(R.id.navigationview);
            listViewmanhinhchinh = (ListView)findViewById(R.id.listviewmanhinhchinh);
            drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
            mangloaisp = new ArrayList<>();

            mangloaisp.add(0,new Loaisp(0,"Trang chủ","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSezDEUYGChOZMdZHGM3BSsEGqzGiSgoT0YNrnGrKRVmm6Qj5OC"));

            loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
            listViewmanhinhchinh.setAdapter(loaispAdapter);


        }
        void GetDuLieuLoaisp()//doc du lieu tu mang
        {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//khai bao context
            //JsonArrayRequest(duongdan,neuThanhCong,neuThatBai);
            //tao request
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.Duongdanloaisp, new Response.Listener<JSONArray>() {
                //xu ly khi thanh cong
                @Override
                public void onResponse(JSONArray response) {
                    if(response!=null)
                    {
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);//lay doi tuong thu i
                                //boc tung doi tuong
                                id = jsonObject.getInt("id");//lay id
                                tenloaisp = jsonObject.getString("tenloaisp");//lay ten sp
                                hinhanhloaisp = jsonObject.getString("hinhanhloaisanpham");//lay hinh anh
                                //dua vao mang
                                mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                                loaispAdapter.notifyDataSetChanged();

                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }

                        }
                        mangloaisp.add(3,new Loaisp(0,"Bán hàng","http://naturalmarketplace.net/wp-content/uploads/2018/05/flash_sale_banner_6822057.jpg"));
                        mangloaisp.add(4,new Loaisp(0,"Liên hệ","https://www.air-it.co.uk/wp-content/uploads/2015/02/kpi-icons-01.png"));
                        mangloaisp.add(5,new Loaisp(0,"Thông tin","http://www.mobilegiving.ca/wp-content/uploads/2015/06/icon_info_lg2.png"));

                    }
                }
                //xu ly khi that bai
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    CheckConnetion.ShowToastLong(getApplicationContext(),error.toString());
                }
            });
            requestQueue.add(jsonArrayRequest);//add request vao xu ly
        }


    /////--------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6_main);
        Anhxa();
        if(CheckConnetion.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ViewFlip();
            GetDuLieuLoaisp();
            ClickItemListview();



        }


    }
    ////demo4

    ///end demo4

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                //Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                //startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }






    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.actionbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
                drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
            }
        });
    }
///--------------------demo6

    ///-----------------------end demo6


}