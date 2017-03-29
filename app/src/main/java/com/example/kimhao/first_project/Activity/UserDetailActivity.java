package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.squareup.picasso.Picasso;

public class UserDetailActivity extends AppCompatActivity {

    private ImageView mImgAva;
    private TextView mTvName;
    private TextView mTvAge;
    private TextView mTvAddr;
    private ImageView mImgFav;
    private int mPos;
    private boolean isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        mImgAva = (ImageView) findViewById(R.id.imgAva);
        mTvName = (TextView) findViewById(R.id.tvName);
        mTvAge = (TextView) findViewById(R.id.tvAge);
        mTvAddr = (TextView) findViewById(R.id.tvAddr);
        mImgFav = (ImageView) findViewById(R.id.imgFavorite);

        mImgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck){
                    mImgFav.setSelected(!isCheck);
                }else {
                    mImgFav.setSelected(!isCheck);
                    isCheck = true;
                }
                Log.d("aaaaaaaa", "onClick: "+isCheck);
            }
        });

        ItemUser user = getIntent().getBundleExtra("user").getParcelable("userclick");
        mPos = getIntent().getIntExtra("Pos",-1);
        isCheck = user.isFavorite();
        Log.d("nhan ", "onCreate: 23232 " + mPos);
        Picasso.with(this)
                .load(user.getImage())
                .fit()
                .centerCrop()
                .into(mImgAva);
        mTvName.setText(user.getName());
        mTvAge.setText(user.getAge());
        mTvAddr.setText(user.getAddress());
        mImgFav.setSelected(user.isFavorite());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("check",isCheck);
        Log.d("select fav: ", "onBackPressed: "+isCheck);
        if (mPos != -1){
            intent.putExtra("Pos",mPos);
        }
        Log.d("Tra ", "onBackPressed: "+mPos);
        setResult(RESULT_OK,intent);
        finish();
    }
}
