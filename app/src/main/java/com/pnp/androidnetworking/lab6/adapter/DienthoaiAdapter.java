package com.pnp.androidnetworking.lab6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.model.Sanpham;

public class DienthoaiAdapter extends BaseAdapter {
    ArrayList<Sanpham> sanphamArrayList;
    Context context;

    public DienthoaiAdapter(Context context,ArrayList<Sanpham> sanphamArrayList) {
        this.sanphamArrayList = sanphamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolderDT {
        ImageView imgdienthoai;
        TextView txtten, txtgia,txtmota;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolderDT viewHolderDT = null;
        //xu ly view
        if(view==null)//neu chua ton tai view, tien hanh tao view
        {
            viewHolderDT = new ViewHolderDT();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_dienthoai,null);
            viewHolderDT.txtten=(TextView)view.findViewById(R.id.textviewdienthoai);
            viewHolderDT.txtgia=(TextView)view.findViewById(R.id.textviewgiadienthoai);
            viewHolderDT.txtmota=(TextView)view.findViewById(R.id.textviewmotadienthoai);
            viewHolderDT.imgdienthoai=(ImageView)view.findViewById(R.id.imageviewdienthoai);
            view.setTag(viewHolderDT);//tao 1 template
        }
        else //neu da ton tai view, ta lay ve view
        {
            viewHolderDT = (ViewHolderDT)view.getTag();
        }
        //xu ly du lieu
        Sanpham sanpham = (Sanpham)getItem(i);
        viewHolderDT.txtten.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolderDT.txtgia.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham()));
        viewHolderDT.txtmota.setMaxLines(3);
        viewHolderDT.txtmota.setText(sanpham.getMotasanpham());
        //load anh
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolderDT.imgdienthoai);
        return view;
    }
}
