package com.example.klim.basemvp.example;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.base.BaseFragmentPresenter;
import com.example.klim.basemvp.base.FragmentView;

public class HelloFragmentPresenter extends BaseFragmentPresenter<HelloFragmentPresenter.HelloView> {

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        getView().setText("Hello!!!");
    }

    public void onAgainClicked() {
        getRouter().showInfoDialog(R.string.hello_title, R.string.hello, null);
    }

    public interface HelloView extends FragmentView {
        void setText(String text);
    }

}
