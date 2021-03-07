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

public class MainActivity2 extends AppCompatActivity {

    private EditText txtProduct;
    private EditText txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        txtProduct = findViewById(R.id.txtProduct);
        txtNum = findViewById(R.id.txtNum);
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
                txtNum.setError("Введите количество товара");
                flag = false;
            }

            if (flag) {
                int finalNum = num;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Внимание");
                builder.setMessage("Вы точно хотите добавить товар?");
                //builder.setIcon(R.drawable.ic_cloud_upload_black_24dp);

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ProductList.addProduct(name, finalNum);
                        Intent intent = new Intent();
                        intent.putExtra("result", name + " - " + finalNum + " шт.");
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