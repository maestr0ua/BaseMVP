package com.example.klim.basemvp.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.transition.Visibility;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.klim.basemvp.utils.AnimationUtils;

public abstract class BaseActivity<P extends BaseActivityPresenter> extends AppCompatActivity
        implements ActivityView {

    private P mPresenter;


    protected abstract P initPresenter();

    protected abstract void findUI();

    protected abstract int getLayoutResource();

    protected abstract void setupUI(Bundle savedInstanceState);

    @Override
    public int getFragmentContainer() {
        return 0;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutResource() != 0)
            setContentView(getLayoutResource());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setupWindowAnimations();
/*
            postponeEnterTransition();
            final View decor = getWindow().getDecorView();
            decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public boolean onPreDraw() {
                    decor.getViewTreeObserver().removeOnPreDrawListener(this);
                    startPostponedEnterTransition();
                    return true;
                }
            });
*/
        }

        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(true);

        findUI();
        setupUI(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.bindView(this);

        mPresenter.onCreateView();

        getWindow().getDecorView().getRootView().getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getWindow().getDecorView().getRootView().getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                        mPresenter.onViewCreated();
                    }
                });

    }

    /*
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }
    }
*/

    @Override
    protected void onDestroy() {
        mPresenter.onDestroyView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            clearWindowAnimations();
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setToolbarTitle(String text) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(text);
        }
    }

    @Override
    public void setToolbarTitle(@StringRes int textRes) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(textRes);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setupWindowAnimations() {
        Visibility transition = AnimationUtils.fadeTransition();
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        getWindow().setReturnTransition(transition);
        getWindow().setReenterTransition(transition);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void clearWindowAnimations() {
        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);
        getWindow().setReturnTransition(null);
        getWindow().setReenterTransition(null);
    }

/*
    @Override
    public void startActivity(Class _activityClass, Bundle _bundle) {
        Intent intent = new Intent(this, _activityClass);
        if (_bundle != null) intent.putExtras(_bundle);
        startActivity(intent);
    }
*/

    @Override
    public void finishActivity() {
        supportFinishAfterTransition();
    }

    @Override
    public Point getDisplaySize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Bundle getViewArguments() {
        return getIntent().getExtras();
    }

    @Override
    public AppCompatActivity asActivity() {
        return this;
    }

    protected final <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }
}
