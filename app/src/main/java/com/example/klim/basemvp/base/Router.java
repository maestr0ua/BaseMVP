package com.example.klim.basemvp.base;


import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

public interface Router {

    void init(ActivityView activity);

    void onSessionExpired();

    void onBackPressed();

    void clearBackStack();

    void replaceFragment(Fragment _fragment, boolean _addToBackStack);

    void replaceFragment(Fragment _fragment, boolean _addToBackStack, View _sharedElement, String _sharedName);

    void showRetryDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener);

    void showInfoDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);

    void showErrorDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);

    void showErrorDialog(@StringRes int _title, String _message, View.OnClickListener _listener);

    void showConfirmDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);

    void showConfirmDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener positiveListener, View.OnClickListener negativeListener);

    void showWarningDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener);

    void showWarningDialog(@StringRes int _title, String _message, View.OnClickListener _listener);

    void showDialog(BaseDialog _dialog, @StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener);

    void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _listener, View.OnClickListener _negativeListener);

    void showDialog(BaseDialog _dialog);

    void showLoadingDialog();

    void hideLoadingDialog();

//    void startActivity(Class _activityClass, Bundle _bundle);

    void startActivity(Class _activityClass, int flags, Bundle _bundle, View... sharedViews);

    void startActivityForResult(Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews);

    void startActivityFromFragment(Fragment _fragment, Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews);

    void finishActivity();

    void startService(Class serviceClass, Bundle extras);

    boolean isServiceRunning(Class<?> serviceClass);

}
