package com.example.kimhao.first_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class ActivityLogin extends AppCompatActivity {

     public final String USER_NAME = "admin";
     public final String PASSWORD = "123";

     @ViewById(R.id.edtUser)
     EditText mEdtUser;

     @ViewById(R.id.edtPass)
     EditText mEdtPass;

     @ViewById(R.id.tvReg)
     TextView mTvReg;

     @ViewById(R.id.imgEye)
     ImageView mImgEye;

     @ViewById(R.id.btnLogin)
     Button mBtnLogin;

     @ViewById(R.id.ckbRemeber)
     CheckBox mCkbRemeber;

     @Click(R.id.tvReg)
     void clickReg() {
          RegisterActivity_.intent(getBaseContext()).start();
     }

     @AfterViews
     void after() {
          boolean result = checkLogin();
          if (result) {
               Intent i = new Intent(ActivityLogin.this, LoginSuccess.class);
               startActivity(i);
          } else {

               mBtnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                Intent i = new Intent(ActivityLogin.this, LoginSuccess.class);
//                SharedPreferences shared = getSharedPreferences("MyShare", MODE_PRIVATE);
//                SharedPreferences.Editor editor = shared.edit();
//
//                editor.putString("URName", mEdtUser.getText().toString());
//                editor.putString("URPass", mEdtPass.getText().toString());
//                editor.commit();
//                Log.d("dsghgdgsfghgg", "onClick: "+mEdtUser.getText().toString());
//                Bundle bundle = new Bundle();
//                bundle.putString("user", String.valueOf(mEdtUser.getText()));
//                bundle.putString("pass", String.valueOf(mEdtPass.getText()));
//                i.putExtra("Show", bundle);
//                startActivity(i);
//                finish();
                    LoginSuccess_.intent(getBaseContext()).Username(mEdtUser.getText().toString()).start();
                    }
               });

               //set onClick RegisterActivity

               //set Listener show password

          }
     }

     @Touch(R.id.imgEye)
     boolean Touch(View v, MotionEvent event) {
          if (event.getAction() == MotionEvent.ACTION_DOWN) {
               mEdtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
          } else {
               mEdtPass.setInputType(129);
          }
          return true;
     }

     private boolean checkLogin() {
          boolean result = false;
          //SharedPreferences share = getSharedPreferences("MyShare", MODE_PRIVATE);
          String user = mEdtUser.getText().toString();
          String pass = mEdtPass.getText().toString();

          if (user.equals(USER_NAME) && pass.equals(PASSWORD)) {
               result = true;
          }
          return result;
     }

}
