package com.topoffers.topoffers.seller.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.topoffers.topoffers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerProductDetailsExtraFragment extends Fragment {
    public SellerProductDetailsExtraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller_product_details_extra, container, false);
    }

}
