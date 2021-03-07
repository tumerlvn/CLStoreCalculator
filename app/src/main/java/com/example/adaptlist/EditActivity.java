package com.example.adaptlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import models.Product;
import models.ProductList;

public class EditActivity extends AppCompatActivity {

    private EditText txtProduct;
    private EditText txtNum;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtProduct = findViewById(R.id.txtProduct);
        txtNum = findViewById(R.id.txtNum);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
    }

    public void onBtnCloseClicked(View v) {
        if (v.getId() == R.id.btnSave) {
            String name = txtProduct.getText().toString();
            int num = -1;
            boolean flag = true;
            try {
                num = Integer.parseInt(txtNum.getText().toString());
            } catch (Exception ex) {

            }

            if (name == null || name.isEmpty()) {
                txtProduct.setError("Введите продукт");
                flag = false;
            }

            if (num <= 0) {
                txtNum.setError("Введите цену товара");
                flag = false;
            }

            if (flag) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Внимание");
                builder.setMessage("Вы точно хотите изменить товар?");
                //builder.setIcon(R.drawable.ic_cloud_upload_black_24dp);

                int finalNum = num;
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Товар изменен",
                                Toast.LENGTH_LONG)
                                .show();

                        Intent intent = new Intent();

                        Product p = ProductList.getProduct(id);
                        p.setTitle(name);
                        p.setPrice(finalNum);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                };

                builder.setPositiveButton("Да", listener);

                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setNeutralButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.setCancelable(false);

                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(this, "Пожалуйста заполните все поля", Toast.LENGTH_LONG).show();
            }
        }
    }
}