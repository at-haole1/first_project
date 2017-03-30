package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.kimhao.first_project.R;

public class SendingActivity extends AppCompatActivity {
    public static final String ACTION_SEND_BROADCAST = "ActionSend";

    private static final int SELECT_PICTURE = 1;
    private String mSelectImagePath;
    private ImageView mImageView;
    private EditText mEdtText;
    private Uri mSelectImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);

        mImageView = (ImageView) findViewById(R.id.imgView);
        mEdtText  = (EditText) findViewById(R.id.edtTextSend);
        Button btnSend  = (Button) findViewById(R.id.btnSend);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendImageIntent = new Intent();
                sendImageIntent.setType("image/*");
                sendImageIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(sendImageIntent,"Select Picture"),SELECT_PICTURE);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(ACTION_SEND_BROADCAST);
                sendIntent.putExtra("mytext",mEdtText.getText());
                sendIntent.putExtra("mypicture",mSelectImageUri.getPath());
                Log.e("mytext", "onClick: "+mEdtText.getText());
                Log.e("mypicture", "onClick: "+mSelectImageUri.getPath());
                sendBroadcast(sendIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE){
                mSelectImageUri = data.getData();
                mImageView.setImageURI(mSelectImageUri);
        }
    }

    public String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}