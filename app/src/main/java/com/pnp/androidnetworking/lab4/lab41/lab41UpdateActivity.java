package com.pnp.androidnetworking.lab4.lab41;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pnp.androidnetworking.R;
import com.pnp.androidnetworking.lab4.lab42.FunctionDelete;

public class lab41UpdateActivity extends AppCompatActivity {
    EditText txtPid,txtName,txtPrice,txtDes;
    Button btnDelete, btnUpdate;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab41_update_main2);
        txtPid = findViewById(R.id.demo4txtPid);
        txtName = findViewById(R.id.demo4txtName);
        txtPrice = findViewById(R.id.demo4txtPrice);
        txtDes = findViewById(R.id.demo4txtDes);
        btnDelete = findViewById(R.id.demo4Delete);
        btnUpdate = findViewById(R.id.demo4Update);
        tvResult = findViewById(R.id.demo4TvResult);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrdUpdate p = new PrdUpdate();
                p.setPid(txtPid.getText().toString());
                p.setName(txtName.getText().toString());
                p.setPrice(txtPrice.getText().toString());
                p.setDescription(txtDes.getText().toString());
                FunctionUpdate f = new FunctionUpdate();
                f.updateFn(tvResult,p);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FunctionDelete f = new FunctionDelete();
                f.deleteFn(tvResult,txtPid.getText().toString());
            }
        });
    }
}
