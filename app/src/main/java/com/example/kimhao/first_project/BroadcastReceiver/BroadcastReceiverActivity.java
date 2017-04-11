package com.example.kimhao.first_project.BroadcastReceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by KimHao on 28/03/2017.
 */
@EActivity
public class BroadcastReceiverActivity extends AppCompatActivity {
    FragmentReceiver mFragmentReceiver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        int info = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            info = extras.getInt("mStatus",0);
        }
        mFragmentReceiver = new FragmentReceiver(info);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mFragmentReceiver).commit();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int info = 0;
        Bundle extras = intent.getExtras();
        if (extras != null){
            info = extras.getInt("mStatus",0);
        }
        mFragmentReceiver.setstat(info);
    }
}
