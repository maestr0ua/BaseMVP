package com.example.klim.basemvp.base;

public abstract class BaseActivityPresenter<V extends ActivityView> extends BasePresenterImpl<V> {

    @Override
    public void onCreateView() {
        super.onCreateView();
        mRouter = new RouterImpl();
        mRouter.init(mView);
    }

}
