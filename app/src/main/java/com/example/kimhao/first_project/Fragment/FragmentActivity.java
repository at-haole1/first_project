package com.example.kimhao.first_project.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.kimhao.first_project.R;

public class FragmentActivity extends AppCompatActivity {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn1 = (Button)findViewById(R.id.btnFragment1);
        btn2 = (Button)findViewById(R.id.btnFragment2);
    }
    public void selectFrag(View view){
        Fragment fr;
        if (view == findViewById(R.id.btnFragment1)){
            fr = new Fragment_one();
        }else {
            fr = new Fragment_two();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place,fr);
        fragmentTransaction.commit();
    }

}
