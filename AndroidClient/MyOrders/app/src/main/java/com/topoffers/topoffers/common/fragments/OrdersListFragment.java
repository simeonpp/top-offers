package com.topoffers.topoffers.common.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.adapters.OrderListAdapter;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Order;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersListFragment extends Fragment {
    private View root;
    private IData<Order> orderData;
    private AuthenticationCookie cookie;
    private Class classToNavigateOnItemClick;
    private ArrayList<Order> mainOrders;
    
    public OrdersListFragment() {
        // Required empty public constructor
        mainOrders = new ArrayList<>();
    }

    public static OrdersListFragment create(IData<Order> orderData, AuthenticationCookie cookie, Class classToNavigateOnItemClick) {
        OrdersListFragment ordersListFragment = new OrdersListFragment();
        ordersListFragment.setOrderData(orderData);
        ordersListFragment.setCookie(cookie);
        ordersListFragment.setClassToNavigateOnItemClick(classToNavigateOnItemClick);
        return ordersListFragment;
    };

    public void setOrderData(IData<Order> orderData) {
        this.orderData = orderData;
    }

    public void setCookie(AuthenticationCookie cookie) {
        this.cookie = cookie;
    }

    public void setClassToNavigateOnItemClick(Class classToNavigateOnItemClick) {
        this.classToNavigateOnItemClick = classToNavigateOnItemClick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_orders_list, container, false);
        this.initOrdersList();
        return root;
    }

    private void initOrdersList() {
        ListView lvOrders = (ListView) root.findViewById(R.id.lv_orders);
        ArrayAdapter<Order> ordersAdapter = new OrderListAdapter(this.getContext());

        lvOrders.setAdapter(ordersAdapter);

        final Context context = this.getContext();
        lvOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order clickedOrder = mainOrders.get(position);

                (DialogFragment.create(context, clickedOrder.getProductTitle(), 0)).show();
            }
        });

        // Perform HTTP Request
        this.loadOrders(ordersAdapter);
    }

    private void loadOrders(final ArrayAdapter<Order> ordersAdapter) {
        final LoadingFragment loadingFragment = LoadingFragment.create(this.getContext(), "Getting orders...");
        loadingFragment.show();

        Headers headers = AuthenticationHelpers.getAuthenticationHeaders(this.cookie);
        orderData.getAll(headers)
            .subscribe(new Consumer<Order[]>() {
                @Override
                public void accept(Order[] orders) throws Exception {
                    mainOrders = new ArrayList<Order>(Arrays.asList(orders));

                    ordersAdapter.clear();
                    ordersAdapter.addAll(orders);

                    loadingFragment.hide();
                }
            });
    }

}