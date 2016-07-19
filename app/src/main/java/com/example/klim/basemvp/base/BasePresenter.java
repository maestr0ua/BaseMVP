package com.example.klim.basemvp.base;

interface BasePresenter<V extends BaseView> {

    Router getRouter();

    void setRouter(Router router);

    void bindView(V _view);

    V getView();

    void onCreateView();

    void onDestroyView();

    void onPause();

    void onResume();

    void onViewCreated();

    void onBackPressed();

}
