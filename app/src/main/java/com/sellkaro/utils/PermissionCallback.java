package com.sellkaro.utils;

/**
 * Created by Pradeep on 08-04-2018.
 */

public interface PermissionCallback {
    void noPermissionRequired();

    void onPermissionGranted();

    void onPermissionDenied();
}
