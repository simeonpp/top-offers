package com.topoffers.topoffers.common.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.topoffers.data.services.ImagesHttpData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.models.Product;

public class ProductListAdapter extends ArrayAdapter<Product> {
//    private final ImagesHttpData imageHttpData;

    public ProductListAdapter(Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("YEEE", "from product lsit adapter");
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.item_product, parent, false);
        }

        Product currentProduct = this.getItem(position);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_product_title);
        tvTitle.setText(currentProduct.getTitle());

        TextView tvPrice = (TextView) view.findViewById(R.id.tv_product_price);
        double productPrice = currentProduct.getPrice();
        String productPriceAsString = String.valueOf(productPrice);
        tvPrice.setText(productPriceAsString);

        return view;
    }
}
