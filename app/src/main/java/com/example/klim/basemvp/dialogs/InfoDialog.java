package com.example.klim.basemvp.dialogs;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.klim.basemvp.R;
import com.example.klim.basemvp.utils.RxUtils;
import com.example.klim.basemvp.base.BaseDialog;


public class InfoDialog extends BaseDialog {
    protected TextView tvTitle;
    protected TextView tvMessage;
    protected TextView btnClose;

    @StringRes
    private int mMessageRes;
    @StringRes
    private int mTitleRes;
    private String mMessage;

    @StringRes
    private int mButtonTitleRes;
    private View.OnClickListener mListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_info_layout;
    }

    @Override
    protected void setupViews(View _rootView) {
        setCancelable(false);
        tvTitle = (TextView) _rootView.findViewById(R.id.tvTitle);
        tvMessage = (TextView) _rootView.findViewById(R.id.tvMessage);
        btnClose = (TextView) _rootView.findViewById(R.id.btnNegative);


        RxUtils.click(btnClose).subscribe(o -> onClick());

        if (mMessageRes != 0) tvMessage.setText(getString(mMessageRes));
        else if (!TextUtils.isEmpty(mMessage)) tvMessage.setText(mMessage);

        if (mTitleRes != 0) tvTitle.setText(getString(mTitleRes));

        if (mButtonTitleRes != 0) btnClose.setText(getString(mButtonTitleRes));
    }

    private void onClick() {
        dismiss();
        if (mListener != null) mListener.onClick(null);
    }

    @Override
    public void setTitle(@StringRes int _title) {
        mTitleRes = _title;
    }

    @Override
    public void setMessage(String _title) {
        mMessage = _title;
    }

    @Override
    public void setMessage(@StringRes int _text) {
        mMessageRes = _text;
    }

    public void setButtonTitle(@StringRes int _title) {
        mButtonTitleRes = _title;
    }

    @Override
    public void setOnPositiveClickListener(View.OnClickListener _listener) {
        mListener = _listener;
    }
}
