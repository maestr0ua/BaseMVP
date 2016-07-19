package com.example.klim.basemvp.dialogs;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.utils.RxUtils;
import com.example.klim.basemvp.base.BaseDialog;

public class ConfirmDialog extends BaseDialog {
    private TextView tvTitle;
    private TextView tvMessage;
    private TextView btnNegative;
    private TextView btnPositive;

    @StringRes
    private int mMessage;
    @StringRes
    private int mTitle;

    @StringRes
    private int mButtonPositiveTitle;
    @StringRes
    private int mButtonNegativeTitle;

    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_confirm_layout;
    }

    @Override
    protected void setupViews(View _rootView) {

        tvTitle = (TextView) _rootView.findViewById(R.id.tvTitle);
        tvMessage = (TextView) _rootView.findViewById(R.id.tvMessage);
        btnNegative = (TextView) _rootView.findViewById(R.id.btnNegative);
        btnPositive = (TextView) _rootView.findViewById(R.id.btnPositive);

        setCancelable(false);

        RxUtils.click(btnNegative).subscribe(o -> {
            if (mNegativeListener != null) mNegativeListener.onClick(null);
            dismiss();
        });
        RxUtils.click(btnPositive).subscribe(o -> {
            if (mPositiveListener != null) mPositiveListener.onClick(null);
            dismiss();
        });

        if (mMessage != 0) tvMessage.setText(getString(mMessage));
        if (mTitle != 0) tvTitle.setText(getString(mTitle));
        if (mButtonPositiveTitle != 0) btnPositive.setText(getString(mButtonPositiveTitle));
        if (mButtonNegativeTitle != 0) btnNegative.setText(getString(mButtonNegativeTitle));
    }

    @Override
    public void setTitle(@StringRes int _title) {
        mTitle = _title;
    }

    @Override
    public void setMessage(@StringRes int _message) {
        mMessage = _message;
    }

    public void setNegativeButtonTitle(@StringRes int _title) {
        mButtonNegativeTitle = _title;
    }

    public void setPositiveButtonTitle(@StringRes int _title) {
        mButtonPositiveTitle = _title;
    }

    @Override
    public void setOnNegativeClickListener(View.OnClickListener _listener) {
        mNegativeListener = _listener;
    }

    @Override
    public void setOnPositiveClickListener(View.OnClickListener _listener) {
        mPositiveListener = _listener;
    }
}
