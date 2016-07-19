package com.example.klim.basemvp.utils;

import android.support.annotation.StringRes;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.exceptions.RetrofitException;
import com.example.klim.basemvp.base.Router;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ErrorHandler {

    @StringRes
    private static final int errorRes = R.string.error;

    private static String parseErrorBody(Throwable throwable) {
        RetrofitException exception = (RetrofitException) throwable;
        BufferedReader reader;
        StringBuilder sb = new StringBuilder();

        reader = new BufferedReader(new InputStreamReader(exception.getResponse().errorBody().byteStream()));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void onLoginError(Router router, Throwable _throwable) {
        handleError(router, _throwable, true);
    }

    public static void onError(Router router, Throwable _throwable) {
        handleError(router, _throwable, false);
    }

    private static void handleError(Router router, Throwable _throwable, boolean isLoginError) {
        Logger.e(_throwable);

        if (_throwable instanceof RetrofitException) {
            RetrofitException exception = (RetrofitException) _throwable;
            switch (exception.getKind()) {
                case NETWORK:
                    router.showErrorDialog(R.string.error_network, R.string.error_bad_network_connection, null);
                    return;
                case HTTP:
                    switch (exception.getResponse().code()) {
                        case 400:
                            if (isLoginError) {
                                router.showErrorDialog(errorRes, parseErrorBody(_throwable), null);
                            } else {
                                router.showErrorDialog(R.string.error_network, exception.getMessage(), null);
                            }
                            return;
                        case 401:
                            if (isLoginError) {
                                router.showErrorDialog(errorRes, R.string.error_wrong_credential, null);
                            } else {
                                router.onSessionExpired();
                            }
                            return;
                        case 404:
                            router.showErrorDialog(R.string.error_network, R.string.error_server_not_responding, null);
                            return;
                        default:
                            String message = _throwable.getMessage() == null ? _throwable.getClass().getName() : _throwable.getMessage();
                            router.showErrorDialog(errorRes, message, null);
                            return;
                    }
                case UNEXPECTED:
                    String message = _throwable.getMessage() == null ? _throwable.getClass().getName() : _throwable.getMessage();
                    router.showErrorDialog(errorRes, message, null);
                    return;
            }
        }

        String message = _throwable.getMessage() == null ? _throwable.getClass().getName() : _throwable.getMessage();
        router.showErrorDialog(errorRes, message, null);
    }

    public static boolean isSessionExpired(Throwable _throwable) {

        if (_throwable instanceof RetrofitException) {
            RetrofitException exception = (RetrofitException) _throwable;
            if (exception.getKind() == RetrofitException.Kind.HTTP) {
                switch (exception.getResponse().code()) {
                    case 401:
                        return true;
                    default:
                        return false;
                }
            }
        }
        return false;

    }
}
