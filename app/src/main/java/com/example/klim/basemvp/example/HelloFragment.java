package com.example.klim.basemvp.example;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.base.BaseFragment;
import com.example.klim.basemvp.utils.RxUtils;

public class HelloFragment extends BaseFragment<HelloFragmentPresenter>
        implements HelloFragmentPresenter.HelloView{

    private TextView tvTitle;
    private Button btnRetry;

    @Override
    protected int getTitle() {
        return R.string.hello_toolbar;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.hello_layout;
    }

    @Override
    protected HelloFragmentPresenter initPresenter() {
        return new HelloFragmentPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        btnRetry = (Button) rootView.findViewById(R.id.btnRetry);
    }

    @Override
    protected void setupUI() {
        RxUtils.click(btnRetry, o -> getPresenter().onAgainClicked());
    }

    @Override
    public void setText(String text) {
        tvTitle.setText(text);
    }
}
