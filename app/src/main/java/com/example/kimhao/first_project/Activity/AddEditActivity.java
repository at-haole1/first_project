package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_edit_user)
public class AddEditActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewById(R.id.imgAvatar)
    ImageView mImgAvatar;

    @ViewById(R.id.edtName)
    EditText mEdtName;

    @ViewById(R.id.edtAge)
    EditText mEdtAge;

    @ViewById(R.id.edtAddr)
    EditText mEdtAddress;

    @ViewById(R.id.btnAddEdit)
    Button mBtnAddEdit;

    @ViewById(R.id.btnDelete)
    Button mBtnDelete;

    private String mValue;
    private ItemUser mUser;
    private DataBaseUser mUserDatabase = new DataBaseUser(this);
    private String path;

    private void init() {
        mImgAvatar.setOnClickListener(this);
        mBtnAddEdit.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @AfterViews
    void afterViews(){
        init();

        mValue = getIntent().getStringExtra("value");

        if (mValue.equals("add")) {
            mBtnAddEdit.setText("ADD");
            mBtnDelete.setVisibility(View.GONE);
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
            mBtnDelete.setVisibility(View.VISIBLE);
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
            case R.id.btnSave:
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
