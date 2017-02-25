package com.topoffers.topoffers.seller.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.topoffers.data.base.IData;
import com.topoffers.data.models.Headers;
import com.topoffers.topoffers.R;
import com.topoffers.topoffers.common.fragments.DialogFragment;
import com.topoffers.topoffers.common.helpers.AuthenticationHelpers;
import com.topoffers.topoffers.common.models.AuthenticationCookie;
import com.topoffers.topoffers.common.models.Product;
import com.topoffers.topoffers.seller.activities.SellerProductsListActivity;
import com.topoffers.topoffers.seller.types.UpdateProductType;

import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProductFragment extends Fragment {
    private View root;
    private IData<Product> productData;
    private AuthenticationCookie cookie;

    private UpdateProductType mode;
    private int productId;

    public UpdateProductFragment() {
        // Required empty public constructor
    }

    public static UpdateProductFragment create(IData<Product> productData, AuthenticationCookie cookie, int productId) {
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        updateProductFragment.setProductData(productData);
        updateProductFragment.setCookie(cookie);
        updateProductFragment.setProductId(productId);
        if (productId == 0) {
            updateProductFragment.setMode(UpdateProductType.CREATE);
        } else {
            updateProductFragment.setMode(UpdateProductType.EDIT);
        }
        return updateProductFragment;
    }

    public void setProductData(IData<Product> productData) {
        this.productData = productData;
    }

    public void setCookie(AuthenticationCookie cookie) {
        this.cookie = cookie;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setMode(UpdateProductType mode) {
        this.mode = mode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_update_product_fragament, container, false);
        this.initProductDetails();
        return root;
    }

    private void initProductDetails() {
        if (this.mode == UpdateProductType.EDIT) {
            this.initEditProduct();
        }

        final Context context = this.getContext();
        Button btnUpdateProduct = (Button) root.findViewById(R.id.btn_update_product);

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = ((EditText) root.findViewById(R.id.et_update_product_title)).getText().toString();
                String description = ((EditText) root.findViewById(R.id.et_update_product_description)).getText().toString();
                int quantity = Integer.parseInt(((EditText) root.findViewById(R.id.et_update_product_quantity)).getText().toString());

                double price = -1;
                String priceAsDouble = ((EditText) root.findViewById(R.id.et_update_product_price)).getText().toString();
                if (!priceAsDouble.isEmpty()) {
                    try {
                        price = Double.parseDouble(priceAsDouble);
                    } catch (Exception e) {
                        (DialogFragment.create(context, "Please enter valid price", 1)).show();
                    }
                }

                if (title.isEmpty() || quantity < 0 || price < 0) {
                    (DialogFragment.create(context, "Invalid input form", 1)).show();
                } else {
                    Product product = new Product(title, price, quantity, null, description);
                    Headers headers = AuthenticationHelpers.getAuthenticationHeaders(cookie);

                    productData.add(product, headers)
                        .subscribe(new Consumer<Product>() {
                            @Override
                            public void accept(Product product) throws Exception {
                                // Notify user
                                String notifyMessage = String.format("%s was successfully added.", title);
                                (DialogFragment.create(context, notifyMessage, 1)).show();

                                // Redirects to products list
                                Intent intent = new Intent(context, SellerProductsListActivity.class);
                                context.startActivity(intent);
                            }
                        });
                }
            }
        });
    }

    private void initEditProduct() {
        TextView tvTitle = (TextView) root.findViewById(R.id.tv_update_product_title);
        tvTitle.setText("Update XXXX");

        Button btnUpdateProduct = (Button) root.findViewById(R.id.btn_update_product);
        btnUpdateProduct.setText("Update Product");
    }
}
