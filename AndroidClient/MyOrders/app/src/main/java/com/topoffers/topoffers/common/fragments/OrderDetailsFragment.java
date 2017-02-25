package com.topoffers.topoffers.common.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.data.services.ImagesHttpData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Order;

import io.reactivex.functions.Consumer;

public class OrderDetailsFragment extends Fragment {
    public static final String INTENT_ORDER_KEY = "intent_order_key";

    private View root;
    private IData<Order> orderIData;
    private AuthenticationCookie cookie;
    private final ImagesHttpData imageHttpData;

    public OrderDetailsFragment() {
        // Required empty public constructor
        this.imageHttpData = new ImagesHttpData();
    }

    public static OrderDetailsFragment create(int orderId, IData<Order> orderData, AuthenticationCookie cookie) {
        OrderDetailsFragment orderDetailsFragment = new OrderDetailsFragment();

        orderDetailsFragment.setOrderIData(orderData);
        orderDetailsFragment.setCookie(cookie);

        Bundle args = new Bundle();
        args.putSerializable(INTENT_ORDER_KEY, orderId);
        orderDetailsFragment.setArguments(args);

        return orderDetailsFragment;
    }

    public void setOrderIData(IData<Order> orderIData) {
        this.orderIData = orderIData;
    }

    public void setCookie(AuthenticationCookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_order_details, container, false);
        this.initOrder();
        return root;
    }

    private void initOrder() {
        final Context context = this.getContext();
        Headers headers = AuthenticationHelpers.getAuthenticationHeaders(this.cookie);

        Bundle arguments = this.getArguments();
        final int orderId = (int) arguments.getSerializable(INTENT_ORDER_KEY);

        orderIData.getById(orderId, headers)
            .subscribe(new Consumer<Order>() {
                @Override
                public void accept(Order order) throws Exception {
                    // Set title
                    TextView tvProductTitle = (TextView) root.findViewById(R.id.tv_order_details_product_title);
                    tvProductTitle.setText(order.getProductTitle());

                    // Set image
                    final ImageView ivImage = (ImageView) root.findViewById(R.id.iv_order_details_image);
                    if (order.getProductImageIdentifier() != null) {
                        String imageUrl = "https://s24.postimg.org/khsw9pbyt/image.png"; // temp solution unitl server is ready
                        imageHttpData.getImage(imageUrl)
                            .subscribe(new Consumer<Bitmap>() {
                                @Override
                                public void accept(Bitmap bitmap) throws Exception {
                                    ivImage.setImageBitmap(bitmap);
                                }
                            });
                    }
                }
            });
    }

}
