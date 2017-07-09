package com.mycompany.androidproject08_dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;


public class CustomDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View view = inflater.inflate(R.layout.fragment_custom_dialog, null);
        Button btnLongin = (Button) view.findViewById(R.id.btnLogin);
        Button btnCacel = (Button) view.findViewById(R.id.btnCancel);

        btnCacel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Login")
                .setView(view)
                .create();
        return dialog;
    }
}
