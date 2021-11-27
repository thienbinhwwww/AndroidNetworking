package com.pnp.androidnetworking.lab4.lab42;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunctionDelete {
    public void deleteFn(TextView tvResult,String pid)
    {
        //1. tao doi tuong
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstDelete.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2. Dua du lieu vao request
        PrdDelete p = new PrdDelete();
        p.setPid(pid);

        RequestDelete requestDelete = new RequestDelete();
        requestDelete.setProducts(p);
        //3. goi interface de thuc thi ham va tra ket qua ve Response
        InterfaceDelete interfaceDelete = retrofit.create(InterfaceDelete.class);
        Call<ResponseDelete> call = interfaceDelete.deleteExe(pid);
        //4. thuc thi ham
        call.enqueue(new Callback<ResponseDelete>() {
            @Override
            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                ResponseDelete responseDelete = response.body();
                tvResult.setText(responseDelete.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });

    }
}
