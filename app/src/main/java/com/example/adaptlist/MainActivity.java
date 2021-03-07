package com.example.adaptlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import models.Product;
import models.ProductAdapter;
import models.ProductList;

public class MainActivity extends AppCompatActivity {

    int ACTIVITY2_REQUEST_CODE = 2;
    int ACTIVITY_EDIT_REQUEST_CODE = 3;

    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView productsListView = findViewById(R.id.productsListView);

        //ProductList.init();

        adapter = new ProductAdapter(this,
                R.layout.product_item,
                ProductList.PRODUCTS);

        productsListView.setAdapter(adapter);

        productsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tag = (int)view.getTag();

                Product p = ProductList.getProduct(tag);
                if (p != null) {
                    Toast.makeText(getApplicationContext(), "clicked: " + tag, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AboutProduct.class);
                    intent.putExtra("id", p.getId());

                    startActivityForResult(intent, ACTIVITY_EDIT_REQUEST_CODE);
                }
            }
        });

        productsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int tag = (int)view.getTag();

                Product p = ProductList.getProduct(tag);
                if (p != null) {
                    Toast.makeText(getApplicationContext(), "Long: " + tag, Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuItemAdd) {
            onBtnClicked(findViewById(R.id.menuItemAdd));
        } else if (item.getItemId() == R.id.menuItemDevelopers) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/tumerlvn/?hl=ru"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBtnClicked(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivityForResult(intent, ACTIVITY2_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY2_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("result");
                Toast.makeText(this, "Act2 successful: " + result, Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Act2 canceled", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == ACTIVITY_EDIT_REQUEST_CODE) {
            adapter.notifyDataSetChanged();
        }
    }
}