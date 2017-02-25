package com.topoffers.topoffers.common.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.adapters.ProductListAdapter;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Product;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsListFragment extends Fragment {
    private View root;

    private IData<Product> productData;

    private AuthenticationCookie cookie;
    private ArrayList<Product> mainProducts;

    public ProductsListFragment() {
        // Required empty public constructor
        mainProducts = new ArrayList<>();
    }

    public static ProductsListFragment create(IData<Product> productData, AuthenticationCookie cookie) {
        ProductsListFragment productsListFragment = new ProductsListFragment();
        productsListFragment.setProductData(productData);
        productsListFragment.setCookie(cookie);
        return productsListFragment;
    };

    public void setProductData(IData<Product> productData) {
        this.productData = productData;
    }

    public void setCookie(AuthenticationCookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_products_list, container, false);
        this.initProductsList();
        return root;
    }

    private void initProductsList() {
        ListView lvProducts = (ListView) root.findViewById(R.id.lv_products);
        ArrayAdapter<Product> productsAdapter = new ProductListAdapter(this.getContext());

        lvProducts.setAdapter(productsAdapter);

        final Context context = this.getContext();
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product clickedProduct = mainProducts.get(position);

                DialogFragment dialogFragment = DialogFragment.create(context, clickedProduct.getTitle(), 0);
                dialogFragment.show();
            }
        });

        // Perform HTTP Request
        this.loadProducts(productsAdapter);
    }

    private void loadProducts(final ArrayAdapter<Product> productsAdapter) {
        final LoadingFragment loadingFragment = LoadingFragment.create(this.getContext(), "Getting products...");
        loadingFragment.show();

        Headers headers = AuthenticationHelpers.getAuthenticationHeaders(this.cookie);

        productData.getAll(headers)
            .subscribe(new Consumer<Product[]>() {
                @Override
                public void accept(Product[] products) throws Exception {
                    mainProducts = new ArrayList<Product>(Arrays.asList(products));

                    productsAdapter.clear();
                    productsAdapter.addAll(products);

                    loadingFragment.hide();
                }
            });
    }
}
