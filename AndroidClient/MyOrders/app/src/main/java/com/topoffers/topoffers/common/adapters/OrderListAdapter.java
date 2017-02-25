package com.topoffers.topoffers.common.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topoffers.data.services.ImagesHttpData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.models.Order;

import io.reactivex.functions.Consumer;

public class OrderListAdapter extends ArrayAdapter<Order> {
    private final ImagesHttpData imageHttpData;

    public OrderListAdapter(Context context) {
        super(context, -1);
        this.imageHttpData = new ImagesHttpData();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.item_order, parent, false);
        }

        Order currentOrder = this.getItem(position);

        // Set title
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_order_title);
        tvTitle.setText(currentOrder.getProductTitle());

//        // Set price
//        TextView tvPrice = (TextView) view.findViewById(R.id.tv_product_price);
//        tvPrice.setText(Utils.convertDoublePriceToStringPriceWithTag(currentProduct.getPrice()));
//
//        // Set seller username
//        TextView tvSellerUsername = (TextView) view.findViewById(R.id.tv_product_seller_username);
//        tvSellerUsername.setText(String.format("by %s", currentProduct.getSellerUsername()));

        // Set image
        final ImageView ivImage = (ImageView) view.findViewById(R.id.iv_order_image);
        if (currentOrder.getProductImageIdentifier() != null) {
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
