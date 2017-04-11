package com.example.kimhao.first_project.Activity;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_demo_toolbar)
@OptionsMenu(R.menu.menu_toolbar)
public class DemoToolbarActivity extends AppCompatActivity implements AlertDiaFragment.EditTextListener {
    @ViewById(R.id.tvToolbar)
    TextView mTvToolBar;

    @AfterViews
    void afterToolbar(){
        getSupportActionBar().setTitle("Demo Toolbar");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(R.id.itemDia)
    void startAlterDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DemoToolbarActivity.this);
        alertDialog.setTitle(getTitle());
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
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
        alertDialog.show();
    }

    @OptionsItem(R.id.itemFrag)
    void startFragmentDialog(){
        FragmentManager fm = getSupportFragmentManager();
        AlertDiaFragment alertDiaFragment =  AlertDiaFragment_.builder().build();
        alertDiaFragment.show(fm,"fghfdg");
    }

    @OptionsItem(android.R.id.home)
    void startHome(){
        finish();
    }
    @Override
    public void onFinishEdittext(String input) {
        mTvToolBar.setText(input);
    }
}
