package com.example.klim.basemvp.base;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;


public interface BaseView {

    Bundle getViewArguments();

    Context getViewContext();

    Point getDisplaySize();

}
