package com.example.kimhao.first_project.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kimhao.first_project.R;


public class PhoneCallActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtNumber;
    private String mStrNumber = "+84 ";
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnSao, btn0, btnThang;
    private ImageView mImgClearn, imgCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);
        init();

    }

    public void init() {
        mEdtNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnThang = (Button) findViewById(R.id.btnThang);
        btnSao = (Button) findViewById(R.id.btnSao);
        mImgClearn = (ImageView) findViewById(R.id.imgClear);
        imgCall = (ImageView) findViewById(R.id.imgCall);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnSao.setOnClickListener(this);
        btnThang.setOnClickListener(this);
        mImgClearn.setOnClickListener(this);
        imgCall.setOnClickListener(this);
        //get data filter
//        Intent intent = getIntent();
//        mStrNumber = intent.getDataString();
//        if(mStrNumber==null){
//            mStrNumber = mStrNumber.substring(4);
//        }
        //// TODO: 15/03/2017 trim data: tel:1234 => 1234
        //mStrNumber = mStrNumber.substring(4,mEdtNumber.length()-1);
        mEdtNumber.setText(mStrNumber);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn0:
                mStrNumber = mStrNumber + "0";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn1:
                mStrNumber = mStrNumber + "1";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn2:
                mStrNumber = mStrNumber + "2";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn3:
                mStrNumber = mStrNumber + "3";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn4:
                mStrNumber = mStrNumber + "4";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn5:
                mStrNumber = mStrNumber + "5";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn6:
                mStrNumber = mStrNumber + "6";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn7:
                mStrNumber = mStrNumber + "7";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn8:
                mStrNumber = mStrNumber + "8";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btn9:
                mStrNumber = mStrNumber + "9";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btnSao:
                mStrNumber = mStrNumber + "*";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.btnThang:
                mStrNumber = mStrNumber + "#";
                mEdtNumber.setText(mStrNumber);
                break;
            case R.id.imgClear:
                if (mStrNumber.length()>0){
                mStrNumber = mStrNumber.substring(0, mEdtNumber.length() - 1);
                mEdtNumber.setText(mStrNumber);
                mEdtNumber.setSelection(mEdtNumber.getText().length());
                }

                break;
            case R.id.imgCall:
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel: "+ mStrNumber));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},0);
                    return;
                }
                Log.d("sdt ",mStrNumber);
                startActivity(phoneIntent);

        }


    }
}
