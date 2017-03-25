package com.example.kimhao.first_project.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

public class AfterLoginActivity extends AppCompatActivity {
    TextView TvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TvShow = (TextView) findViewById(R.id.tvShow);
        TvShow.setText("Đăng nhập thành công");
    }

}
