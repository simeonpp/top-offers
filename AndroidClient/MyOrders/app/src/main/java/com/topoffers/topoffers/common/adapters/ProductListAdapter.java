package com.topoffers.topoffers.common.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topoffers.data.services.ImagesHttpData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.helpers.Utils;
import com.topoffers.topoffers.common.models.Product;

import io.reactivex.functions.Consumer;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private final ImagesHttpData imageHttpData;

    public ProductListAdapter(Context context) {
        super(context, -1);
        this.imageHttpData = new ImagesHttpData();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.item_product, parent, false);
        }

        Product currentProduct = this.getItem(position);

        // Set title
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_product_title);
        tvTitle.setText(currentProduct.getTitle());

        // Set price
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_product_price);
        tvPrice.setText(Utils.convertDoublePriceToStringPriceWithTag(currentProduct.getPrice()));

        // Set seller username
        TextView tvSellerUsername = (TextView) view.findViewById(R.id.tv_product_seller_username);
        tvSellerUsername.setText(String.format("by %s", currentProduct.getSellerUsername()));

        // Set image
        final ImageView ivImage = (ImageView) view.findViewById(R.id.iv_product_image);
        if (currentProduct.getImageIdentifier() != null) {
//            String imageUrl = "https://unsplash.com/photos/" + currentProduct.getImageIdentifier(); // temp until server is ready
            String imageUrl = "https://s24.postimg.org/khsw9pbyt/image.png";
            imageHttpData.getImage(imageUrl)
                    .subscribe(new Consumer<Bitmap>() {
                        @Override
                        public void accept(Bitmap bitmap) throws Exception {
                            ivImage.setImageBitmap(bitmap);
                        }
                    });
        }

        return view;
    }
}
