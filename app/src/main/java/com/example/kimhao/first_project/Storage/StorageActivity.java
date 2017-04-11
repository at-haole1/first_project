package com.example.kimhao.first_project.Storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.EActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
@EActivity
public class StorageActivity extends AppCompatActivity {
    Button mBtnReadData, mBtnSave;
    TextView mTvXuat;
    private String simpleFile = "note.txt";
    EditText mEdtYourText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mBtnReadData = (Button) findViewById(R.id.btnReadData);
        mEdtYourText = (EditText)findViewById(R.id.edtWriteYourText);
        mBtnSave = (Button) findViewById(R.id.btnSaveData);
        mTvXuat = (TextView) findViewById(R.id.tvXuat);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSaveData();
            }
        });

        mBtnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readData();
            }
        });
    }




    public void readData(){
        try{
            FileInputStream inputStream = openFileInput(simpleFile);
            //InputStream inputStream = getResources().openRawResource
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String data = "";
            StringBuilder builder = new StringBuilder();
            while ((data=reader.readLine())!=null){
                builder.append(data);
                builder.append("\n");
            }
            inputStream.close();
            mTvXuat.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doSaveData(){
        String data = this.mEdtYourText.getText().toString();
        try {
            //open stream file
            FileOutputStream out = StorageActivity.this.openFileOutput(simpleFile,MODE_PRIVATE);
            //write data
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this,"File save",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

//    public void readData_2(){
//        String data;
//        InputStream inputStream = getResources().openRawResource(R.drawable.mytext);
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        StringBuilder builder = new StringBuilder();
//        if (inputStream!=null){
//            try{
//                while ((data=bufferedReader.readLine())!=null){
//                    builder.append(data);
//                    builder.append("\n");
//                }
//                inputStream.close();
//                mEdtYourText.setText(builder.toString());
//            } catch (IOException e) {
//                Log.e("Error", "readData_2: "+e.getMessage());
//                e.printStackTrace();
//            }
//        }
//    }
}
