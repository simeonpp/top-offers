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
import android.widget.Toast;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Header;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Product;

import java.util.ArrayList;
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
    private ArrayList<Product> products;

    public ProductsListFragment() {
        // Required empty public constructor
        products = new ArrayList<>();
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
        ArrayAdapter<Product> productsAdapter = this.getProductsAdapter();

        lvProducts.setAdapter(productsAdapter);

        final Context context = this.getContext();
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product clickedProduct = products.get(position);

                Toast
                    .makeText(context, clickedProduct.getTitle(), Toast.LENGTH_SHORT)
                    .show();
            }
        });

        // Perform HTTP Request
        this.loadProducts(productsAdapter);
    }

    private ArrayAdapter<Product> getProductsAdapter() {
        ArrayAdapter<Product> productsAdapter = new ArrayAdapter<Product>(root.getContext(), -1, products) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
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
        };

        return  productsAdapter;
    }

    private void loadProducts(final ArrayAdapter<Product> productsAdapter) {
        final LoadingFragment loadingFragment = LoadingFragment.create(this.getContext(), "Getting products...");
        loadingFragment.show();

        Headers headers = AuthenticationHelpers.getAuthenticationHeaders(this.cookie);

        productData.getAll(headers)
            .subscribe(new Consumer<Product[]>() {
                @Override
                public void accept(Product[] products) throws Exception {
                    productsAdapter.clear();
                    productsAdapter.addAll(products);
                    loadingFragment.hide();
                }
            });
    }
}
