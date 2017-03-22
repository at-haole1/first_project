package com.example.kimhao.first_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AfterLoginActivity extends AppCompatActivity {
    TextView TvShowUser, TvShowPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TvShowUser = (TextView) findViewById(R.id.tvShowUser);
        TvShowPass = (TextView) findViewById(R.id.tvShowPass);
        TvShowUser.setText("Đăng nhập thành công");
//        Intent i = getIntent();
//        Bundle bundle = i.getBundleExtra("Show");
//        TvShowUser.setText(bundle.getString("user"));
//        TvShowPass.setText(bundle.getString("pass"));

    }

}
