package com.pnp.androidnetworking.lab6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab6.model.Loaisp;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> loaispArrayList;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> loaispArrayList, Context context) {
        this.loaispArrayList = loaispArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaispArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaispArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imgloaisp;
        TextView txttenloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        //tao template cho viewholder
        if(view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_listview_loaisp,null);
            viewHolder.txttenloaisp = (TextView)view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView)view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);//tao template
        }
        else {//da co view
            viewHolder = (ViewHolder)view.getTag();//lay ve view qua template
        }
        //do du lieu
        Loaisp loaisp = (Loaisp)getItem(i);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
        //lay anh qua mang (Picasso)
        Picasso.get().load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.home)
                .error(R.drawable.erro)
                .into(viewHolder.imgloaisp);
        return view;
    }
}
