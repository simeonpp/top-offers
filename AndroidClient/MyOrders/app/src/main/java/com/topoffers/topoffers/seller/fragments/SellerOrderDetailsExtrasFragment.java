package com.topoffers.topoffers.seller.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.fragments.DialogFragment;
import com.topoffers.topoffers.common.fragments.OrderDetailsFragment;
import com.topoffers.topoffers.common.helpers.Utils;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerOrderDetailsExtrasFragment extends Fragment {
    private static final String BUNDLE_ARGUMENTS_ORDER_KEY = "bundle_arguments_product_key";

    private View root;
    private IData<Order> orderData;
    private AuthenticationCookie cookie;
    private OrderDetailsFragment orderDetailsFragment;

    private Button buttonMarkAsSend;
    private Button buttonMarkAsRejected;

    public SellerOrderDetailsExtrasFragment() {
        // Required empty public constructor
    }

    public static SellerOrderDetailsExtrasFragment create(int orderId, IData<Order> orderData, AuthenticationCookie cookie, OrderDetailsFragment orderDetailsFragment) {
        SellerOrderDetailsExtrasFragment sellerOrderDetailsExtrasFragment = new SellerOrderDetailsExtrasFragment();

        sellerOrderDetailsExtrasFragment.setOrderData(orderData);
        sellerOrderDetailsExtrasFragment.setCookie(cookie);
        sellerOrderDetailsExtrasFragment.setOrderDetailsFragment(orderDetailsFragment);

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_ARGUMENTS_ORDER_KEY, orderId);
        sellerOrderDetailsExtrasFragment.setArguments(args);

        return sellerOrderDetailsExtrasFragment;
    }

    public void setOrderData(IData<Order> orderData) {
        this.orderData = orderData;
    }

    public void setCookie(AuthenticationCookie cookie) {
        this.cookie = cookie;
    }

    public void setOrderDetailsFragment(OrderDetailsFragment orderDetailsFragment) {
        this.orderDetailsFragment = orderDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_seller_order_details_extras, container, false);
        this.addButtonsListeners();
        return root;
    }

    private void addButtonsListeners() {
        final Context context = this.getContext();
        Bundle arguments = this.getArguments();
        final int productId = (int) arguments.getSerializable(BUNDLE_ARGUMENTS_ORDER_KEY);

        buttonMarkAsSend = (Button) root.findViewById(R.id.btn_order_history_mark_as_send);
        buttonMarkAsSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DialogFragment.create(context, "Send", 0))).show();
                handleChangeStatusChange("send");
            }
        });

        buttonMarkAsRejected = (Button) root.findViewById(R.id.btn_order_history_mark_as_rejected);
        buttonMarkAsRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DialogFragment.create(context, "Rejected", 0))).show();
                handleChangeStatusChange("rejected");
            }
        });
    }

    private void handleChangeStatusChange(String newStatus) {
        // Remove action buttons
        buttonMarkAsRejected.setVisibility(View.GONE);
        buttonMarkAsSend.setVisibility(View.GONE);

        // Update status Text view
        TextView tvOrderStatus = (TextView) orderDetailsFragment.getView().findViewById(R.id.tv_order_details_status);
        tvOrderStatus.setText(Utils.buildDetailsString("Status", newStatus.toUpperCase()));
        tvOrderStatus.setTextColor(Color.parseColor(Utils.getOrderStatusColor(newStatus)));
    }

}
