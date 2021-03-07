package models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.adaptlist.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int resource;
    private ArrayList<Product> products;

    public ProductAdapter(@NonNull Context context, int resource,
                          @NonNull ArrayList<Product> products) {
        super(context, resource, products);

        this.context = context;
        this.resource = resource;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null);

        TextView lblTitle = view.findViewById(R.id.lblTitle);
        TextView lblPrice = view.findViewById(R.id.lblPrice);

        Product p = this.products.get(position);

        lblTitle.setText(p.getTitle());
        lblPrice.setText(p.getPrice() + " tg");

        view.setTag(p.getId());

        return view;
    }
}
