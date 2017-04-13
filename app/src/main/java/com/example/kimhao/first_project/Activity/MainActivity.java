package com.example.kimhao.first_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.kimhao.first_project.API.ApiActivity_;
import com.example.kimhao.first_project.BroadcastReceiver.BroadcastReceiverActivity_;
import com.example.kimhao.first_project.Maps.MapsActivity_;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.Service.ServiceActivity_;
import com.example.kimhao.first_project.SharedPreferences.SharedPreferencesActitvity_;
import com.example.kimhao.first_project.Storage.StorageActivity_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.btnLoginAc)
    Button mBtnLogin;

    @ViewById(R.id.btnRecyclerview)
    Button mBtnRecyclerview;

    @ViewById(R.id.btnMentorTai)
    Button mMentorTai;

    @ViewById(R.id.btnPhoneCall)
    Button mBtnPhoneCall;

    @ViewById(R.id.btnSharedPreferences)
    Button mBtnSharedPreferences;

    @ViewById(R.id.btnStorage)
    Button mBtnStorage;

    @ViewById(R.id.btnFragment)
    Button mBtnFragment;

    @ViewById(R.id.btnService)
    Button mBtnService;

    @ViewById(R.id.btnReceiver)
    Button mBtnReceiver;

    @ViewById(R.id.btnToolbar)
    Button mBtnToolbar;

    @ViewById(R.id.btnSending)
    Button mBtnSending;

    @ViewById(R.id.btnMap)
    Button mBtnMap;

    @ViewById(R.id.btnAPI)
    Button mBtnAPI;

    @ViewById(R.id.btnAsync)
    Button mBtnAsync;

    @Click(R.id.btnLoginAc)
        void clickBtnLogin() {
            ActivityLogin_.intent(this).start();
    }

    @Click(R.id.btnRecyclerview)
        void clickBtnRecyclerview() {
            ListUserActivity_.intent(this)

                    .start();
    }

    @Click(R.id.btnMentorTai)
    void clickBtnMentorTai() {
        MentorTaiActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnPhoneCall)
    void clickBtnPhoneCall() {
        PhoneCallActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnSharedPreferences)
    void clickbtnSharedPreferences() {
        SharedPreferencesActitvity_.intent(this)
                .start();
    }

    @Click(R.id.btnStorage)
    void clickbtnStorage() {
        StorageActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnFragment)
    void clickbtnFragment() {
        FragmentActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnService)
    void clickbtnService() {
        ServiceActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnReceiver)
    void clickbtnReceiver() {
        BroadcastReceiverActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnToolbar)
    void clickbtnToolbar() {
        DemoToolbarActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnSending)
    void clickbtnSending() {
        SendingActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnMap)
    void clickbtnMap() {
        MapsActivity_.intent(this)
                .start();
    }

    @Click(R.id.btnAPI)
    void clickbtnAPI() {
        ApiActivity_.intent(this)
                .myMapsActivity("start API Activity")
                .start();
    }

    @Click(R.id.btnAsync)
    void clickbtnAsync() {
        AsyncTaskActivity_.intent(this)
                .start();
    }

}
