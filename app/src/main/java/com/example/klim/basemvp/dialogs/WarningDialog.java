package com.example.klim.basemvp.dialogs;

import com.example.klim.basemvp.R;

public class WarningDialog extends ConfirmDialog {

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_warning_layout;
    }
}
