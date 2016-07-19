package com.example.klim.basemvp.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment
        implements FragmentView {

    private View rootView;
    private P mPresenter;
    private BaseActivity mBaseActivity;

    protected abstract int getTitle();

    protected abstract int getLayoutResource();

    protected abstract P initPresenter();

    protected abstract void findUI(View rootView);

    protected abstract void setupUI();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(getLayoutResource(), container, false);
        findUI(rootView);

        mBaseActivity = (BaseActivity) getActivity();

        if (mPresenter == null) {
            setupUI();
            mPresenter = initPresenter();
            mPresenter.setRouter(mBaseActivity.getPresenter().getRouter());
            if (getPresenter() == null)
                new ClassCastException("Presenter is not created + " + this.getClass().getName());
        }
        getPresenter().bindView(this);
        getPresenter().onCreateView();

        return rootView;
    }

    @Override
    public Bundle getViewArguments() {
        return getArguments();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                getPresenter().onViewCreated();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        getPresenter().onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        getPresenter().onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data);
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public Fragment asFragment() {
        return this;
    }

    @Override
    public Point getDisplaySize() {
        return mBaseActivity.getDisplaySize();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    protected ActivityView getParentView() {
        return mBaseActivity;
    }

    protected final <T extends View> T findView(@IdRes int id) {
        return (T) rootView.findViewById(id);
    }

}
