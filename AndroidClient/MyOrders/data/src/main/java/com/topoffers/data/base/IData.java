package com.topoffers.data.base;

import com.topoffers.data.models.Headers;

import io.reactivex.Observable;

public interface IData<T> {
    Observable<T[]> getAll(Headers headers);
    Observable<T> getById(Object id, Headers headers); // id can be string or integer
    Observable<T> add(T object, Headers headers);
    Observable<T> custom(RequestWithBodyType requestType, Object object);
}