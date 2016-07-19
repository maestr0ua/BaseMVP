package com.example.klim.basemvp.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.utils.RxUtils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseDialog extends DialogFragment {

    private int mContentResource = getLayoutResource();

    private CompositeSubscription mSubscriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        super.onCreateView(_inflater, _container, _savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        View rootView = _inflater.inflate(R.layout.dialog_base_layout, _container, false);
        if (mContentResource != 0) {
            FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.content_container_BDL);
            frameLayout.removeAllViews();
            frameLayout.addView(_inflater.inflate(mContentResource, frameLayout, false));
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View _view, Bundle _savedInstanceState) {
        super.onViewCreated(_view, _savedInstanceState);
        setupViews(_view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxUtils.unsubscribeIfNotNull(mSubscriptions);
    }

    protected void addSubscription(Subscription _subscription) {
        mSubscriptions.remove(_subscription);
        mSubscriptions.add(_subscription);
    }

    protected abstract int getLayoutResource();

    protected abstract void setupViews(View _rootView);

    public void setTitle(@StringRes int _title) {
        new UnsupportedOperationException();
    }

    ;

    public void setMessage(String _title) {
        new UnsupportedOperationException();
    }

    ;

    public void setMessage(@StringRes int _message) {
        new UnsupportedOperationException();
    }

    ;

    public void setOnPositiveClickListener(View.OnClickListener _listener) {
        new UnsupportedOperationException();
    }

    public void setOnNegativeClickListener(View.OnClickListener _listener) {
        new UnsupportedOperationException();
    }


}
