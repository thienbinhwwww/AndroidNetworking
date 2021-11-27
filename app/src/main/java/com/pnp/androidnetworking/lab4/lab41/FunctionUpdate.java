package com.pnp.androidnetworking.lab4.lab41;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunctionUpdate {
    public void updateFn(TextView tvResult, PrdUpdate p)
    {
        //1.Tao doi tuong
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstUpdate.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2. Dua du lieu can update vao request
        RequestUpdate requestUpdate = new RequestUpdate();
        requestUpdate.setProducts(p);
        //3. Goi ham tu interface
        InterfaceUpdate interfaceUpdate=retrofit.create(InterfaceUpdate.class);
        Call<ResponseUpdate> call = interfaceUpdate.updateExe(p.getPid(),p.getName(),p.getPrice(),p.getDescription());
        //4. thuc thi
        call.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                //get data
                ResponseUpdate responseUpdate = response.body();
                tvResult.setText(responseUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}
