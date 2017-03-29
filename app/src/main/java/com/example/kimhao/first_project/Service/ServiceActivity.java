package com.example.kimhao.first_project.Service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kimhao.first_project.R;

/**
 * Created by KimHao on 28/03/2017.
 */

public class ServiceActivity extends AppCompatActivity {


    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        btnStart = (Button) findViewById(R.id.btnStartService);
        btnStop = (Button) findViewById(R.id.btnStopService);
        btnStart.setOnClickListener(Start_Click);
        btnStop.setOnClickListener(Stop_Click);
    }

    View.OnClickListener Start_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent play = new Intent(ServiceActivity.this, MyService.class);
            startService(play);

        }
    };
    View.OnClickListener Stop_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent play = new Intent(ServiceActivity.this, MyService.class);
            stopService(play);
        }
    };
}
