package com.example.kimhao.first_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimhao.first_project.SQLite.DBHelper;
import com.example.kimhao.first_project.model.ItemUsers;
import com.squareup.picasso.Picasso;

public class DetailRecyclerViewActivity2 extends AppCompatActivity {

    private ItemUsers mItemUsersGet;
    private TextView mTvAge, mTvAddr,mTvName;
    private ImageView mImgAva;
    private DBHelper mDbHelper;
    private int mPosition;
    private String mPath;
    private static final int SELECT_PICTURE = 100;
    private Button mBtnSave, mBtnEdit,mBtnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__transfer__recycler_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTvName = (TextView) findViewById(R.id.edtNameAdd);
        mTvAge = (TextView) findViewById(R.id.edtAgeAdd);
        mTvAddr = (TextView) findViewById(R.id.edtAddrAdd);
        mImgAva = (ImageView) findViewById(R.id.imgAvaAdd);
        mBtnSave = (Button) findViewById(R.id.btnSaveAdd);
        mBtnEdit = (Button) findViewById(R.id.btnEdit);
        mBtnDelete =(Button) findViewById(R.id.btnDelete);

        mDbHelper = new DBHelper(this);

        mImgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGallerry = new Intent();
                intentGallerry.setType("image/*");
                intentGallerry.addCategory(Intent.CATEGORY_OPENABLE);
                Log.d("Tôi muốn biết", "onClick: Chọn ảnh được chưa");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    intentGallerry.setAction(Intent.ACTION_OPEN_DOCUMENT);
                    startActivityForResult(intentGallerry, SELECT_PICTURE);
                } else {
                    intentGallerry.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intentGallerry, "Select Picture"), SELECT_PICTURE);
                }
            }
        });
        changeData();
        updateData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                String imagePath = data.getData().toString();
                Log.d("Đường dẫn ảnh", "onActivityResult: " + imagePath);
                if (imagePath != null) {
                    // Get the path from the Uri
                    mPath = imagePath;
                    // Set the image in ImageView
                    mImgAva.setImageURI(data.getData());
                    Log.d("Đường dẫn ảnh", "onActivityResult: " + mPath);
                }else {
                    mImgAva.setImageResource(R.drawable.ic_person);
                    Log.d("Đường dẫn ảnh", "onActivityResult: " + mPath);
                }
            }
        }
    }


    private void changeData(){
        Bundle bundle = getIntent().getExtras();
        String key =   getIntent().getStringExtra("key");
        Log.d("Tôi muốn biết Key: ", "changeData: "+key);
        if ("AddContact".equals(key)){
            mBtnEdit.setVisibility(View.GONE);
            mBtnDelete.setVisibility(View.GONE);
        }else{

            mItemUsersGet = getIntent().getBundleExtra("intent").getParcelable("Data");
            Log.e("Size list get", "changeData: " );
            mBtnSave.setVisibility(View.GONE);
            mBtnEdit.setVisibility(View.VISIBLE);
            mBtnDelete.setVisibility(View.VISIBLE);
            Log.d("SetText:  ", "changeData: "+mItemUsersGet.getName());
            mTvName.setText(mItemUsersGet.getName());
            mTvAge.setText(mItemUsersGet.getAge());
            mTvAddr.setText(mItemUsersGet.getAddr());
            //mPath = mItemUsersGet.getImgAva().toString();
            Picasso.with(this).load(mItemUsersGet.getImgAva()).into(mImgAva);
        }
    }

    private void updateData(){
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Tôi muốn biết:1 ", "onClick: "+mPath);
                Log.d("Tôi muốn biết:2 ", "onClick: "+mTvName.getText());
                Log.d("Tôi muốn biết:3 ", "onClick: "+mTvAge.getText());
                Log.d("Tôi muốn biết:4 ", "onClick: "+mTvAddr.getText());
                if (mDbHelper.addContact(new ItemUsers(
                        mPath,mTvName.getText().toString(),
                        mTvAge.getText().toString(),
                        mTvAddr.getText().toString())))

                {
                    Toast.makeText(DetailRecyclerViewActivity2.this,"Add Contact Sucessful",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailRecyclerViewActivity2.this,"Add Contact Error",Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(DetailRecyclerViewActivity2.this,ListRecyclerViewActivity1.class);
                startActivity(i);
            }
        });

        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDbHelper.updateContact(new ItemUsers(mItemUsersGet.getId(),
                        mPath,mTvName.getText().toString(),
                        mTvAge.getText().toString(),
                        mTvAddr.getText().toString()))){
                    Toast.makeText(DetailRecyclerViewActivity2.this,"Edit Contact Sucessful",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailRecyclerViewActivity2.this,"Edit Contact Error",Toast.LENGTH_SHORT).show();
                }
                finish();
                Intent i = new Intent(DetailRecyclerViewActivity2.this,ListRecyclerViewActivity1.class);
                startActivity(i);
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHelper.deleteContact(new ItemUsers(mItemUsersGet.getId(),
                        mPath,mTvName.getText().toString(),
                        mTvAge.getText().toString(),
                        mTvAddr.getText().toString()));
                finish();
                Intent i = new Intent(DetailRecyclerViewActivity2.this,ListRecyclerViewActivity1.class);
                startActivity(i);
            }
        });
    }

}
