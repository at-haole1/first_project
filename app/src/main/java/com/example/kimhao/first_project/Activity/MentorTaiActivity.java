package com.example.kimhao.first_project.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kimhao.first_project.R;

public class MentorTaiActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCall, btnMessenger, btnMail, btnWeb, btnCHPlay, btnMap, btnSelect, btnCamera;
    ImageView imgPic;
    final static int RESULT_LOAD_IMAGE=1;
    private static final int CAMERA_REQUEST= 1888;
    private static final int SELECT_PICTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_tai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnMessenger = (Button) findViewById(R.id.btnMessenger);
        btnMail = (Button) findViewById(R.id.btnMail);
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnCHPlay = (Button) findViewById(R.id.btnCHPlay);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnSelect = (Button)findViewById(R.id.btnPicture);
        btnCamera = (Button) findViewById(R.id.btnCamera);
        imgPic = (ImageView) findViewById(R.id.imgPickPic);

        btnCall.setOnClickListener(this);
        btnMessenger.setOnClickListener(this);
        btnMail.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnCHPlay.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnCamera.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCall:
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                intentCall.setData(Uri.parse("tel:123123"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE},0);
                    return;
                }
                startActivity(intentCall);
                break;
            case R.id.btnMessenger:
                String sms = "Open SMS Default";
                Intent intentSMS = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: 123445"));
                intentSMS.putExtra("sms_body",sms);
                intentSMS.putExtra("compose_mode", true);
                startActivity(intentSMS);
                finish();
                break;

            case R.id.btnMail:
                String mail = "Send mail";
                Intent intentMail = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "tai.nguyen@asiantech.vn", null));
                intentMail.putExtra(Intent.EXTRA_SUBJECT,  "Inter Hao");
                intentMail.putExtra(Intent.EXTRA_TEXT, mail);
                startActivity(Intent.createChooser(intentMail, "Send email..."));
                break;

            case R.id.btnWeb:
                String url = "http://www.vnexpress.net";
                Intent intentWeb = new Intent(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse(url));
                startActivity(intentWeb);
                break;

            case  R.id.btnCHPlay:
                final String appPackageName =  getPackageName();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+appPackageName)));
                break;

            case  R.id.btnMap:
                Uri gmmIntentUri = Uri.parse("geo:16.2,108");
                Intent intentMap = new Intent(Intent.ACTION_VIEW);
                intentMap.setData(gmmIntentUri);
                Intent chooser = Intent.createChooser(intentMap, "Launch Maps");
                // /intentMap.setPackage("com.google.android.apps.maps");
                startActivity(chooser);
                break;

            case  R.id.btnPicture:
                // TODO: 16/03/2017
//                Intent intentGetPic = new Intent(Intent.ACTION_GET_CONTENT);
//                intentGetPic.setType("image/*");
//
//                Intent intentPic = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intentPic.setType("image/*");
//
//                Intent chooserIntentPic = Intent.createChooser(intentGetPic,"Select Picture");
//                chooserIntentPic.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intentPic});
//
//                int PICK_IMAGE =1;
//                startActivityForResult(chooserIntentPic, PICK_IMAGE);
                // TODO: 16/03/2017 edit
                Intent intentSelectPic =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intentSelectPic.setType("image/*");
                intentSelectPic.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intentSelectPic,"Select Picturn"),SELECT_PICTURE);
                break;
            case  R.id.btnCamera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, CAMERA_REQUEST);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i("Main", "Image Path : " + path);
                    // Set the image in ImageView
                    imgPic.setImageURI(selectedImageUri);
                }
            }
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgPic.setImageBitmap(photo);
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
