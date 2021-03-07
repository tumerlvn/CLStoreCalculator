package com.example.adaptlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import models.Product;
import models.ProductList;

public class AboutProduct extends AppCompatActivity {

    int ACTIVITY_EDIT_REQUEST_CODE = 1;

    TextView productView;
    TextView costView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");


        productView = findViewById(R.id.productView);
        costView = findViewById(R.id.costView);

        String name = ProductList.getProduct(id).getTitle();
        int price = ProductList.getProduct(id).getPrice();

        productView.setText(name);
        costView.setText(price + " tg");
    }

    public void onBtnClicked(View v) {
        if (v.getId() == R.id.deleteBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Внимание");
            builder.setMessage("Вы точно хотите удалить товар?");

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    ProductList.deleteProduct(id);
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

        } else if (v.getId() == R.id.editBtn) {
                Intent editIntent = new Intent(getApplicationContext(), EditActivity.class);
                editIntent.putExtra("id", id);
                startActivityForResult(editIntent, ACTIVITY_EDIT_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name = ProductList.getProduct(id).getTitle();
        int price = ProductList.getProduct(id).getPrice();

        productView.setText(name);
        costView.setText(price + " tg");
    }
}