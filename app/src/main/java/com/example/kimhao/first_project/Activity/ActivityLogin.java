package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

public class ActivityLogin extends AppCompatActivity implements View.OnTouchListener {

    public final String USER_NAME = "admin";
    public final String PASSWORD = "123";
    private EditText mEdtUser, mEdtPass;
    private Button mBtnLogin;
    private TextView mTvReg;
    private ImageView mImgEye;
    private CheckBox mCkbRemeber;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boolean result = checkLogin();
        if (result) {
            Intent i = new Intent(ActivityLogin.this, AfterLoginActivity.class);
            startActivity(i);
            finish();
        } else {
            mEdtUser = (EditText) findViewById(R.id.edtUser);
            mEdtPass = (EditText) findViewById(R.id.edtPass);
            mBtnLogin = (Button) findViewById(R.id.btnLogin);
            mTvReg = (TextView) findViewById(R.id.tvReg);
            mImgEye = (ImageView) findViewById(R.id.imgEye);


            //set onClick Button ActivityLogin
            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(ActivityLogin.this, AfterLoginActivity.class);

                    SharedPreferences shared = getSharedPreferences("MyShare", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();

                    editor.putString("URName", mEdtUser.getText().toString());
                    editor.putString("URPass", mEdtPass.getText().toString());
                    editor.commit();
                    Log.d("dsghgdgsfghgg", "onClick: "+mEdtUser.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putString("user", String.valueOf(mEdtUser.getText()));
                    bundle.putString("pass", String.valueOf(mEdtPass.getText()));
                    i.putExtra("Show", bundle);
                    startActivity(i);
                    finish();
                }
            });

            //set onClick RegisterActivity
            mTvReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ActivityLogin.this, RegisterActivity.class);
                    startActivity(i);
                }
            });

            //set Listener show password
            mImgEye.setOnTouchListener(this);
        }
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            mEdtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{
            mEdtPass.setInputType(129);
        }

        return true;
    }

    private boolean checkLogin(){
        boolean result = false;
        SharedPreferences share = getSharedPreferences("MyShare",MODE_PRIVATE);
        String user = share.getString("URName","");
        String pass = share.getString("URPass","");

        if (user.equals(USER_NAME)&&pass.equals(PASSWORD)){
            result =true;
        }
        return result;
    }

}
