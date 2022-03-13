package com.example.encard02.common;

import androidx.annotation.NonNull;

public class Resource<T> {

    public final T data;
    public final String message;
    public final Status status;

    public Resource(T data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(data, null, Status.SUCCESS);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(null, null, Status.LOADING);
    }

    public static <T> Resource<T> error(String msg) {
        return new Resource<>(null, msg, Status.ERROR);
    }

    public enum Status {
        SUCCESS,
        LOADING,
        ERROR
    }

}
