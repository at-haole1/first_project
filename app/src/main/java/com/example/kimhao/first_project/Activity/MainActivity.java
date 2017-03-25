package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SharedPreferences.SharedPreferencesActitvity;
import com.example.kimhao.first_project.Storage.StorageActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBtnLogin, mBtnRecyclerview, mMentorTai,mBtnPhoneCall,mBtnService,mBtnSharedPreferences,mBtnStorage;
    private Button mBtnFragment;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnRecyclerview = (Button) findViewById(R.id.btnRecyclerview);
        mBtnPhoneCall = (Button) findViewById(R.id.btnPhoneCall);
        mMentorTai = (Button) findViewById(R.id.btnMMentorTai);
        mBtnSharedPreferences = (Button)findViewById(R.id.btnSharedPreferences);
        mBtnStorage = (Button)findViewById(R.id.btnStorage);
        mBtnFragment = (Button) findViewById(R.id.btnFragment);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityLogin.class);
                startActivity(i);
            }
        });
        mBtnRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ListUserActivity.class);
                startActivity(i);
            }
        });

        mBtnPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PhoneCallActivity.class);
                startActivity(i);
            }
        });

        mMentorTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MentorTaiActivity.class);
                startActivity(i);
            }
        });

        mBtnSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SharedPreferencesActitvity.class);
                startActivity(i);
            }
        });
        mBtnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, StorageActivity.class);
                startActivity(i);
            }
        });
        mBtnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(i);
            }
        });
    }


}
