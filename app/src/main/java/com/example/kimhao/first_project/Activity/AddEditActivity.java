package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.SQLiteData.DataBaseUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.Model.ItemUser;
import com.squareup.picasso.Picasso;

public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvTitle;
    private ImageView mImgAvatar;
    private EditText mEdtName;
    private EditText mEdtAge;
    private EditText mEdtAddress;
    private Button mBtnAddEdit;
    private Button mBtnRemove;
    private String mValue;
    private ItemUser mUser;
    private DataBaseUser mUserDatabase = new DataBaseUser(this);
    private String path;

    private void init() {
        mImgAvatar = (ImageView) findViewById(R.id.imgAvatar);
        mEdtName = (EditText) findViewById(R.id.edtName);
        mEdtAge = (EditText) findViewById(R.id.edtAge);
        mEdtAddress = (EditText) findViewById(R.id.edtAddr);
        mBtnAddEdit = (Button) findViewById(R.id.btnAdd);
        mBtnRemove = (Button) findViewById(R.id.btnDelete);
        mImgAvatar.setOnClickListener(this);
        mBtnAddEdit.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        init();

        mValue = getIntent().getStringExtra("value");

        if (mValue.equals("add")) {
            mBtnAddEdit.setText("ADD");
            mBtnRemove.setVisibility(View.GONE);
        } else {
            mUser = getIntent().getBundleExtra("object").getParcelable("data");
            //mImgAvatar.setImageURI(Uri.parse(mUser.getImage()));
            Picasso.with(this)
                    .load(mUser.getImage())
                    .fit()
                    .centerCrop()
                    .error(R.drawable.ic_person)
                    .into(mImgAvatar);
            mEdtName.setText(mUser.getName());
            mEdtAge.setText(mUser.getAge());
            mEdtAddress.setText(mUser.getAddress());
            mBtnAddEdit.setText("EDIT");
            mBtnRemove.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAvatar:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose a photo"), 5);
                break;
            case R.id.btnAdd:
                if (mValue.equals("add")) { // add
                    if (!path.equals("") && mEdtName.getText().toString() != null
                            && mEdtAge.getText().toString() != null && mEdtAge.getText().toString() != null) {
                        ItemUser user = new ItemUser();
                        user.setName(mEdtName.getText().toString());
                        user.setAge(mEdtAge.getText().toString());
                        user.setAddress(mEdtAddress.getText().toString());
                        user.setImage(path);
                        Log.d("111111", "onClick: " + path);
                        mUserDatabase.insertUser(user);
                    }
                } else { // edit
                    ItemUser user = new ItemUser(mUser.getId(), path, mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtAddress.getText().toString());
                    mUserDatabase.updateUser(user);
                }
                Intent it = new Intent(AddEditActivity.this, ListUserActivity.class);
                startActivity(it);
                finish();
                break;
            case R.id.btnDelete:
                Intent i = new Intent(AddEditActivity.this, ListUserActivity.class);
                mUserDatabase.deleteUser(mUser.getId());
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && null != data) {
            Uri mSelectedImageUri = data.getData();
            path = mSelectedImageUri.toString();
            if (null != mSelectedImageUri) {
                Picasso.with(this)
                        .load(path)
                        .fit()
                        .centerCrop()
                        .into(mImgAvatar);
            }
        }
    }
}
