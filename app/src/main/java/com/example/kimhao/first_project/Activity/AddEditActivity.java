package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kimhao.first_project.Model.ItemUser;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.SQLiteData.DataBaseUser;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_edit_user)
public class AddEditActivity extends AppCompatActivity{
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

    @Extra
    int mPosition;

    @Extra
    ItemUser mUser;

    @Extra
    String mValue;

    @Click(R.id.imgAvatar)
    void clickImg(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Choose a photo"), 5);
    }

    @Click(R.id.btnAddEdit)
    void clickAddEdit(){
        if (mValue.equals("add")) { // add
            Log.e("value", "clickAddEdit: "+path );
            if (path != null && mEdtName.getText().toString() != null
                    && mEdtAge.getText().toString() != null && mEdtAge.getText().toString() != null) {
                ItemUser user = new ItemUser();
                user.setName(mEdtName.getText().toString());
                user.setAge(mEdtAge.getText().toString());
                user.setAddress(mEdtAddress.getText().toString());
                user.setImage(path);
//                Log.d("111111", "onClick: " + path);
                mUserDatabase.insertUser(user);
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else { // edit
            //path = mUser.getImage();
            ItemUser user = new ItemUser(mUser.getId(), path, mEdtName.getText().toString(), mEdtAge.getText().toString(), mEdtAddress.getText().toString());
            mUserDatabase.updateUser(user);
            Log.e("path", "clickAddEdit: "+mUser.getImage() );
        }
        ListUserActivity_.intent(getBaseContext()).start();
        finish();
    }

    @Click(R.id.btnDelete)
    void clickDelete(){
        mUserDatabase.deleteUser(mUser.getId());
        ListUserActivity_.intent(getBaseContext()).start();
        finish();
    }

    private DataBaseUser mUserDatabase = new DataBaseUser(this);
    private String path;

    @AfterViews
    void afterViews(){
        if (mValue.equals("add")) {
            mBtnAddEdit.setText("ADD");
            mBtnDelete.setVisibility(View.GONE);
        } else {
            //mImgAvatar.setImageURI(Uri.parse(mUser.getImage()));
            Log.e("aaaaaaaaaaa", "afterViews: "+mUser.getImage() );
            path = mUser.getImage();
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
    public void onBackPressed() {
        ListUserActivity_.intent(getBaseContext());
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
