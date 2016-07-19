package com.example.klim.basemvp.dialogs;

import android.view.View;

import com.example.klim.basemvp.R;

public class ErrorDialog extends InfoDialog {

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_error_layout;
    }

    @Override
    protected void setupViews(View _rootView) {
        super.setupViews(_rootView);
        setCancelable(false);
        if (tvTitle.getText().toString().isEmpty()) {
            tvTitle.setText(R.string.error);
        }
    }
}
