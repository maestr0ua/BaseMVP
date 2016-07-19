package com.example.klim.basemvp;

import android.app.Application;

import com.example.klim.basemvp.utils.ForegroundManager;

public class App extends Application implements ForegroundManager.Listener  {

    private static App instance;
    private static boolean isForeground = false;
    private ForegroundManager foregroundManager;

    public static App getInstance() {
        return instance;
    }

    public boolean isForeground() {
        return isForeground;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        foregroundManager = ForegroundManager.init(this);
        foregroundManager.addListener(this);
    }

    @Override
    public void onBecameForeground() {
        isForeground = true;
    }

    @Override
    public void onBecameBackground() {
        isForeground = false;
    }

}
