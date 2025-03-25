package com.example.tracknba;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class CustomDialogBar {
    Context context;
    Dialog dialog;

    public CustomDialogBar(Context context) {
        this.context = context;
    }

    public void ShowDialog(String description) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView progressTextView = dialog.findViewById(R.id.progress_TextView);
        progressTextView.setText(description);
        dialog.create();
        dialog.show();
    }

    public void HideDialog() {
        dialog.dismiss();
    }
}
