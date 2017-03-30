package com.example.kimhao.first_project.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.kimhao.first_project.R;

/**
 * Created by KimHao on 30/03/2017.
 */

public class AlertDiaFragment extends DialogFragment{
    private EditText mEdtDia;

    public AlertDiaFragment() {
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.fragment_dialog, null,false);
//        mEdtDia = (EditText) view.findViewById(R.id.edtDiaFrag);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        mEdtDia.setOnEditorActionListener(this);
//        return new AlertDialog.Builder(getContext())
//                .setTitle("AlertFragment")
//                .setView(view)
//                .setPositiveButton(android.R.string.ok,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                EditTextListener editTextListener = (EditTextListener) getActivity();
//                                editTextListener.onFinishEdittext(mEdtDia.getText().toString());
//                                dismiss();
//                            }
//                        })
//                .setNegativeButton("Đánh", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).create();
////        AlertDialog alertDialog = builder.create();
////        alertDialog.show();
//
//        //return builder;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container,false);
        mEdtDia = (EditText) view.findViewById(R.id.edtDiaFrag);
        Button btnSendDia = (Button)view.findViewById(R.id.btnSendDia);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btnSendDia.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditTextListener editTextListener = (EditTextListener) getActivity();
               editTextListener.onFinishEdittext(mEdtDia.getText().toString());
               dismiss();
           }
       });
        return view;
    }

    public interface EditTextListener {
        void  onFinishEdittext(String input);
    }
}
