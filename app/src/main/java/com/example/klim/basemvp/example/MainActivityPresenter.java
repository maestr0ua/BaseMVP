package com.example.klim.basemvp.example;


import com.example.klim.basemvp.base.ActivityView;
import com.example.klim.basemvp.base.BaseActivityPresenter;

public class MainActivityPresenter extends BaseActivityPresenter<MainActivityPresenter.MainActivityView>{

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        getRouter().replaceFragment(new HelloFragment(), false);
    }

    public interface MainActivityView extends ActivityView {

    }
}
