package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_user_detail)
public class UserDetailActivity extends AppCompatActivity {

    @ViewById(R.id.imgAva)
    ImageView mImgAva;

    @ViewById(R.id.tvName)
    TextView mTvName;

    @ViewById(R.id.tvAge)
    TextView mTvAge;

    @ViewById(R.id.tvAddr)
    TextView mTvAddr;

    @ViewById(R.id.imgFavorite)
    ImageView mImgFav;

    @Extra
    int mPos;

    @Extra
    ItemUser mUser;

    private boolean isCheck;

    @Click(R.id.imgFavorite)
    void clickFavorite(){
        if (isCheck){
            mImgFav.setSelected(!isCheck);
        }else {
            mImgFav.setSelected(!isCheck);
            isCheck = true;
        }
        Log.d("aaaaaaaa", "onClick: "+isCheck);
    }

    @AfterViews
    void after(){
        isCheck = mUser.isFavorite();
        Log.d("nhan ", "onCreate: 23232 " + mPos);
        Picasso.with(this)
                .load(mUser.getImage())
                .fit()
                .centerCrop()
                .into(mImgAva);
        mTvName.setText(mUser.getName());
        mTvAge.setText(mUser.getAge());
        mTvAddr.setText(mUser.getAddress());
        mImgFav.setSelected(mUser.isFavorite());
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
