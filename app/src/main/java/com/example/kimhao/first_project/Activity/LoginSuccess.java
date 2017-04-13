package com.example.kimhao.first_project.Activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login_success)
public class LoginSuccess extends AppCompatActivity {

    @ViewById(R.id.tvShow)
    TextView TvShow;

    @ViewById(R.id.btnLogout)
    Button mBtnLogout;

    @Extra
    String Username;

    @AfterViews
    void show(){
        TvShow.setText(Username);
    }

    @Click(R.id.btnLogout)
    void logout(){
        finish();
    }
}
