package com.example.klim.basemvp.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.klim.basemvp.App;
import com.example.klim.basemvp.utils.Logger;
import com.example.klim.basemvp.R;
import com.example.klim.basemvp.dialogs.ConfirmDialog;
import com.example.klim.basemvp.dialogs.ErrorDialog;
import com.example.klim.basemvp.dialogs.InfoDialog;
import com.example.klim.basemvp.dialogs.LoadingDialog;
import com.example.klim.basemvp.dialogs.WarningDialog;


public class RouterImpl implements Router {

    private ActivityView mActivity;
    private LoadingDialog progressDialog;
    private BaseDialog mDialog;


    @Override
    public void init(ActivityView activity) {
        mActivity = activity;

        mActivity.asActivity().getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            BaseFragment fragment = (BaseFragment) mActivity.asActivity().getSupportFragmentManager()
                    .findFragmentById(mActivity.getFragmentContainer());
            mActivity.setToolbarTitle(fragment.getTitle());

        });

    }

    @Override
    public void onSessionExpired() {
        showWarningDialog(R.string.error_network, R.string.error_session_expired, view -> {
            startActivity(null, 0, null);
            finishActivity();
        });
    }

    @Override
    public void clearBackStack() {
        mActivity.asActivity().getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackPressed() {
        if (mActivity.asActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
            mActivity.asActivity().getSupportFragmentManager().popBackStack();
        } else
            mActivity.finishActivity();

    }

    @Override
    public void replaceFragment(Fragment _fragment, boolean _addToBackStack) {
        replaceFragment(_fragment, _addToBackStack, null, "");
    }

    @Override
    public void replaceFragment(Fragment _fragment, boolean _addToBackStack, View _sharedElement, String _sharedName) {
        if (mActivity.getFragmentContainer() == 0) {
            new UnsupportedOperationException("There are not container for fragment" + getClass().getName());
        } else {

            FragmentTransaction fragmentTransaction = mActivity.asActivity().getSupportFragmentManager().beginTransaction();
            if (_addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }

            Fragment fragment = mActivity.asActivity().getSupportFragmentManager().findFragmentById(mActivity.getFragmentContainer());
            if (fragment != null) fragmentTransaction.hide(fragment);

            if (mActivity.asActivity().getSupportFragmentManager().getBackStackEntryCount() > 0 || _addToBackStack) {
                fragmentTransaction.add(mActivity.getFragmentContainer(), _fragment, _fragment.getClass().getName());
            } else {
                fragmentTransaction.replace(mActivity.getFragmentContainer(), _fragment, _fragment.getClass().getName());
            }
            fragmentTransaction.show(_fragment);

            if (_sharedElement != null)
                fragmentTransaction.addSharedElement(_sharedElement, _sharedName);

            fragmentTransaction.commit();
            mActivity.setToolbarTitle(((BaseFragment) _fragment).getTitle());
        }

    }

    @Override
    public void startActivityForResult(Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//            Pair<View, String>[] pairs = new Pair[sharedViews.length];
//            for (int i = 0; i < sharedViews.length; i++) {
//                pairs[i] = Pair.create(sharedViews[i], sharedViews[i].getTransitionName());
//            }
//
//            pairs = TransitionHelper.createSafeTransitionParticipants(mActivity.asActivity(), false, pairs);
//            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity.asActivity(), pairs);
//
//            Intent intent = new Intent(mActivity.getViewContext(), _activityClass);
//            if (flags != 0) intent.setFlags(flags);
//            if (_bundle != null) intent.putExtras(_bundle);
//            mActivity.asActivity().startActivityForResult(intent, requestCode, transitionActivityOptions.toBundle());
//        } else {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivityForResult(intent, requestCode);
//        }

    }

    @Override
    public void startActivityFromFragment(Fragment _fragment, Class _activityClass, int flags, Bundle _bundle, int requestCode, View... sharedViews) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//            Pair[] pairs = new Pair[sharedViews.length];
//            for (int i = 0; i < sharedViews.length; i++) {
//                pairs[i] = Pair.create(sharedViews[i], sharedViews[i].getTransitionName());
//            }
//
//            pairs = TransitionHelper.createSafeTransitionParticipants(mActivity.asActivity(), true, pairs);
//            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity.asActivity(), pairs);
//
//            Intent intent = new Intent(mActivity.asActivity(), _activityClass);
//            if (flags != 0) intent.setFlags(flags);
//            if (_bundle != null) intent.putExtras(_bundle);
//            mActivity.asActivity().startActivityFromFragment(_fragment, intent, requestCode, transitionActivityOptions.toBundle());
//        } else {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivityFromFragment(_fragment, intent, requestCode);
//        }

    }

    @Override
    public void startActivity(Class _activityClass, int flags, Bundle _bundle, View... sharedViews) {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//            Pair<View, String>[] pairs = new Pair[sharedViews.length];
//            for (int i = 0; i < sharedViews.length; i++) {
//                pairs[i] = Pair.create(sharedViews[i], sharedViews[i].getTransitionName());
//            }
//
//            pairs = TransitionHelper.createSafeTransitionParticipants(mActivity.asActivity(), false, pairs);
//            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity.asActivity(), pairs);
//
//            Intent intent = new Intent(mActivity.asActivity(), _activityClass);
//            if (flags != 0) intent.setFlags(flags);
//            if (_bundle != null) intent.putExtras(_bundle);
//            mActivity.asActivity().startActivity(intent, transitionActivityOptions.toBundle());
//        } else {
        Intent intent = new Intent(mActivity.asActivity(), _activityClass);
        if (flags != 0) intent.setFlags(flags);
        if (_bundle != null) intent.putExtras(_bundle);
        mActivity.asActivity().startActivity(intent);
//        }
    }


    @Override
    public void finishActivity() {
        mActivity.finishActivity();
    }

    @Override
    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) mActivity.asActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void startService(Class serviceClass, Bundle extras) {
        Intent intent = new Intent(mActivity.asActivity(), serviceClass);
        intent.putExtras(extras);
        mActivity.asActivity().startService(intent);
    }

    @Override
    public void showRetryDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener) {
        WarningDialog dialog = new WarningDialog();
        dialog.setTitle(_title);
        dialog.setMessage(_message);
        dialog.setPositiveButtonTitle(R.string.text_btn_retry);
        dialog.setOnPositiveClickListener(_positiveListener);
        dialog.setOnNegativeClickListener(_negativeListener);
        showDialog(dialog);
    }

    @Override
    public void showInfoDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
        showDialog(new InfoDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showErrorDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
        showDialog(new ErrorDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showErrorDialog(@StringRes int _title, String _message, View.OnClickListener _listener) {
        showDialog(new ErrorDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showWarningDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
        showDialog(new WarningDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showWarningDialog(@StringRes int _title, String _message, View.OnClickListener _listener) {
        showDialog(new WarningDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showConfirmDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _listener) {
        showDialog(new ConfirmDialog(), _title, _message, _listener, null);
    }

    @Override
    public void showConfirmDialog(@StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener) {
        showDialog(new ConfirmDialog(), _title, _message, _positiveListener, _negativeListener);
    }


    @Override
    public void showDialog(BaseDialog _dialog, @StringRes int _title, @StringRes int _message, View.OnClickListener _positiveListener, View.OnClickListener _negativeListener) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.setTitle(_title);
        mDialog.setMessage(_message);
        mDialog.setOnPositiveClickListener(_positiveListener);
        mDialog.setOnNegativeClickListener(_negativeListener);
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");

    }

    @Override
    public void showDialog(BaseDialog _dialog, @StringRes int _title, String _message, View.OnClickListener _listener, View.OnClickListener _negativeListener) {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.setTitle(_title);
        mDialog.setMessage(_message);
        mDialog.setOnPositiveClickListener(_listener);
        mDialog.setOnNegativeClickListener(_negativeListener);
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void showDialog(BaseDialog _dialog) {
        if (mDialog != null && mDialog.isVisible()) mDialog.dismiss();
        mDialog = _dialog;
        mDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new LoadingDialog();
        }
        if (!progressDialog.isShowing() && App.getInstance().isForeground())
            progressDialog.show(mActivity.asActivity().getSupportFragmentManager(), "");
    }

    @Override
    public void hideLoadingDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismissAllowingStateLoss();
        } catch (IllegalStateException e) {
            Logger.e(e);
        }
    }

}
