package com.studiobethejustice.boostcamp_3_android;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WaitDialog extends Dialog {

    private Context context;
    private TextView txWating;
    private ProgressBar progressBar;

    public WaitDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_waiting);

        txWating = findViewById(R.id.text_waiting);
        progressBar = findViewById(R.id.progressbar);

    }


}
