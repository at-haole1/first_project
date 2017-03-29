package com.example.kimhao.first_project.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

public class RegisterActivity extends AppCompatActivity implements View.OnTouchListener {
    private EditText mEdtUser,mEdtPass;
    private Button mBtnReg;
    private RadioGroup mGrRd;
    private RadioButton mRdSex;
    private CheckBox mChkNN,mChkTT,mChkKhac, mChkDS;
    private ImageView mImgEye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEdtUser = (EditText) findViewById(R.id.edtUser);
        mEdtPass = (EditText) findViewById(R.id.edtPass);
        mBtnReg = (Button) findViewById(R.id.btnReg);

        mGrRd = (RadioGroup) findViewById(R.id.grRd);
        mChkNN = (CheckBox) findViewById(R.id.chkNn);
        mChkTT = (CheckBox) findViewById(R.id.chkTt);
        mChkDS = (CheckBox) findViewById(R.id.chkDs);
        mChkKhac = (CheckBox) findViewById(R.id.chkKhac);
        mImgEye = (ImageView) findViewById(R.id.imgEye);

        mImgEye.setOnTouchListener(this);

        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mThongbao="";
                mThongbao = "Username: " + mEdtUser.getText() + " \n Password: " + mEdtPass.getText();
                int i = mGrRd.getCheckedRadioButtonId();
                if (i==-1 ){
                    Toast.makeText(RegisterActivity.this,"Vui lòng chọn Sex",Toast.LENGTH_SHORT).show();
                }
                else{
                    mRdSex = (RadioButton) findViewById(i);
                    mThongbao = mThongbao + " \n " + mRdSex.getText();

                    if (mChkNN.isChecked()){
                        mThongbao = mThongbao + "\n " + mChkNN.getText();
                    }

                    if (mChkTT.isChecked()){
                        mThongbao = mThongbao + "\n " + mChkTT.getText();
                    }

                    if (mChkDS.isChecked()){
                        mThongbao = mThongbao + "\n " + mChkDS.getText();
                    }

                    if (mChkKhac.isChecked()){
                        mThongbao = mThongbao + "\n " + mChkKhac.getText();
                    }

                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Thông tin cá nhân");
                    builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setMessage(mThongbao);
                    builder.create().show();
                }


            }
        });


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            mEdtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            mEdtPass.setInputType(129);
        }

        return true;
    }
}
