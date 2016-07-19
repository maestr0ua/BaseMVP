package com.example.klim.basemvp.base;

import android.content.Intent;
import android.os.Bundle;

import com.example.klim.basemvp.utils.ErrorHandler;
import com.example.klim.basemvp.R;
import com.example.klim.basemvp.utils.RxBus;
import com.example.klim.basemvp.utils.RxUtils;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    protected Router mRouter;
    protected CompositeSubscription mSubscriptions;

    @Override
    public Router getRouter() {
        return mRouter;
    }

    @Override
    public void setRouter(Router router) {
        mRouter = router;
    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroyView() {
        RxUtils.unsubscribeIfNotNull(mSubscriptions);
    }

    protected <T> void execute(Observable<T> request, Action1<T> onSuccess, Action1<Throwable> onError, Action0 onComplete) {
        if (onComplete == null)
            getRouter().showLoadingDialog();
        if (request != null) {
            addSubscription(request.subscribe(
                    t -> onExecuteSuccess(t, onSuccess),
                    throwable -> onExecutingError(throwable, onError),
                    () -> onExecutingComplete(onComplete)));
        }
    }

    protected <T> void execute(Observable<T> request, Action1<T> onSuccess, Action1<Throwable> onError) {
        execute(request, onSuccess, onError, null);
    }

    protected <T> void execute(Observable<T> request, Action1<T> onSuccess) {
        execute(request, onSuccess, null, null);
    }

    protected <T> void execute(Observable<T> request) {
        execute(request, null, null, null);
    }


    private <T> void onExecuteSuccess(T t, Action1<T> onSuccess) {
        if (onSuccess != null) onSuccess.call(t);
    }

    private void onExecutingError(Throwable throwable, Action1<Throwable> errorCallBack) {

        if (!ErrorHandler.isSessionExpired(throwable)) {
            if (errorCallBack != null) {
                errorCallBack.call(throwable);
            } else {
                ErrorHandler.onError(getRouter(), throwable);
            }
        } else {
            onSessionExpired();
        }
    }

    private void onExecutingComplete(Action0 onComplete) {
        if (onComplete == null) {
            getRouter().hideLoadingDialog();
        } else {
            onComplete.call();
        }
    }

    private void onSessionExpired() {
        getRouter().showWarningDialog(R.string.error, R.string.error_session_expired, view1 -> {
            getRouter().finishActivity();
            int flags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
            getRouter().startActivity(null, flags, null);
        });
    }

    protected <T> void connectToBus(Class<T> eventClass, Action1<T> onPublish) {
        addSubscription(RxBus.getInstance().connect(eventClass).subscribe(t -> {
            onPublish.call(t);
        }));
    }

    @Override
    public void bindView(V _view) {
        mView = _view;
    }

    @Override
    public V getView() {
        return mView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void addSubscription(Subscription _subscription) {
        if (mSubscriptions == null)
            mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.remove(_subscription);
        mSubscriptions.add(_subscription);
    }

    protected Bundle getArguments() {
        return getView().getViewArguments();
    }

    @Override
    public void onBackPressed() {
        mRouter.onBackPressed();
    }

}
