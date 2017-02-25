package com.topoffers.data.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImagesHttpData {
    private final OkHttpClient httpClient;

    public ImagesHttpData() {
        this.httpClient = new OkHttpClient();
    }

    public Observable<Bitmap> getImage(final String url) {
        return Observable
            .create(new ObservableOnSubscribe<Bitmap>() {
                @Override
                public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = httpClient.newCall(request).execute();
                    InputStream resultStream = response.body().byteStream();

                    Bitmap bitmap = BitmapFactory.decodeStream(resultStream);
                    e.onNext(bitmap);
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
