package com.example.klim.basemvp.example;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.base.BaseActivity;

public class MainActivity extends BaseActivity<MainActivityPresenter> {

    private Toolbar mToolbar;

    @Override
    protected MainActivityPresenter initPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    protected void findUI() {
        mToolbar = findView(R.id.toolbar);
    }

    @Override
    public int getFragmentContainer() {
        return R.id.frame_container;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
    }

}
