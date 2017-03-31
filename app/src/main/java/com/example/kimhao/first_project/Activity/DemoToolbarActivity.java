package com.example.kimhao.first_project.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

public class DemoToolbarActivity extends AppCompatActivity implements AlertDiaFragment.EditTextListener {
    private TextView mTvToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Demo Toolbar");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        mTvToolBar = (TextView) findViewById(R.id.tvToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemDia:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DemoToolbarActivity.this);
                alertDialog.setTitle(item.getTitle());
                final EditText input = new EditText(DemoToolbarActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTvToolBar.setText(input.getText().toString());
                            }
                        });
//                alertDialog.setPositiveButton("Cancel",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                            }
//                        });
                alertDialog.show();
                break;
            case R.id.itemFrag:
                FragmentManager fm = getSupportFragmentManager();
                AlertDiaFragment alertDiaFragment =  new AlertDiaFragment();
                alertDiaFragment.show(fm,"fghfdg");
                break;
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishEdittext(String input) {
        mTvToolBar.setText(input);
    }
}
