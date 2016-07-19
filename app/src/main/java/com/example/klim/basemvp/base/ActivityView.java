package com.example.klim.basemvp.base;


import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

public interface ActivityView extends BaseView {

    int getFragmentContainer();

    void setToolbarTitle(@StringRes int textRes);

    void setToolbarTitle(String text);

    void finishActivity();

    AppCompatActivity asActivity();

}
