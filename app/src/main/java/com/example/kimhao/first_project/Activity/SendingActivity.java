package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

public class SendingActivity extends AppCompatActivity {
    public static final String ACTION_TEXT = "Action_text";
    public static final String ACTION_PICTURE = "Action_picture";


    private static final int SELECT_PICTURE = 1;
    private String mSelectImagePath;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);

        mImageView = (ImageView) findViewById(R.id.imgView);
        Button btnSendText  = (Button) findViewById(R.id.btnSendText);
        btnSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendTextIntent = new Intent();
                sendTextIntent.setAction(ACTION_TEXT);
                sendTextIntent.putExtra("mytext",
                        "This is my text to send");
                sendTextIntent.putExtra("MyKey","This is second my text to send using my key");
                sendBroadcast(sendTextIntent);
            }
        });

        Button btnSendImage = (Button) findViewById(R.id.btnSendImage);
        btnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendImageIntent = new Intent();
                sendImageIntent.setType("image/*");
                sendImageIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(sendImageIntent,"Select Picture"),SELECT_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri selectImageUri = data.getData();
                mSelectImagePath = getPath(selectImageUri);

                //System.out.println("Image Path: "+mSelectImagePath);
                mImageView.setImageURI(selectImageUri);
                //Toast.makeText(this, , Toast.LENGTH_SHORT).show();
                sendImage(selectImageUri);
            }
        }
    }

    void sendImage(Uri selectedImageUri){
        Intent shareIntent = new Intent();
        shareIntent.setAction(ACTION_PICTURE);
        shareIntent.putExtra("sendpicture",selectedImageUri);
        Toast.makeText(this, selectedImageUri.getPath(), Toast.LENGTH_SHORT).show();
        //shareIntent.setType("image/*");
        sendBroadcast(shareIntent);
    }

    public String getPath(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}