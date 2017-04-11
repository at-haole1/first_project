package com.example.kimhao.first_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.EActivity;

@EActivity
public class LoginSuccess extends AppCompatActivity {
    TextView TvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        TvShow = (TextView) findViewById(R.id.tvShow);
        TvShow.setText("Đăng nhập thành công");
    }
}
