package com.example.kimhao.first_project.Activity;

import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.EditText;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by KimHao on 30/03/2017.
 */
@EFragment(R.layout.fragment_dialog)
public class AlertDiaFragment extends DialogFragment{
    @ViewById(R.id.edtDiaFrag)
    EditText mEdtDia;

    @ViewById(R.id.btnSendDia)
    Button btnSendData;

    public AlertDiaFragment() {
    }

    @Click(R.id.btnSendDia)
    void clickBtnSendData(){
        EditTextListener editTextListener = (EditTextListener) getActivity();
        editTextListener.onFinishEdittext(mEdtDia.getText().toString());
        dismiss();
    }
    public interface EditTextListener {
        void  onFinishEdittext(String input);
    }

}
