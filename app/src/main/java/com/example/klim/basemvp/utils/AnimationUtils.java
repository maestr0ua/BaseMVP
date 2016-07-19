package com.example.klim.basemvp.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;

import com.example.klim.basemvp.App;
import com.example.klim.basemvp.R;

public abstract class AnimationUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Visibility fadeTransition() {
        Visibility transition = new Fade();
        transition.setDuration(App.getInstance().getResources().getInteger(R.integer.anim_duration_long));
        transition.excludeTarget(android.R.id.statusBarBackground, true);
        transition.excludeTarget(android.R.id.navigationBarBackground, true);
        return transition;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Visibility slideTransition() {
        Slide transition = new Slide();
        transition.setDuration(App.getInstance().getResources().getInteger(R.integer.anim_duration_long));
        transition.excludeTarget(android.R.id.statusBarBackground, true);
        transition.excludeTarget(android.R.id.navigationBarBackground, true);
        return transition;
    }

}
