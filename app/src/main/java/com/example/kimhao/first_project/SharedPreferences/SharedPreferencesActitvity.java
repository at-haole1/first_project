package com.example.kimhao.first_project.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

public class SharedPreferencesActitvity extends AppCompatActivity {
    private SeekBar seekBarSound;
    private SeekBar seekBarBrightness;

    private RadioGroup radioGroupDiffLevel;
    private RadioButton radioButtonEasy;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_prefences);

        seekBarSound = (SeekBar)findViewById(R.id.sbSound);
        seekBarBrightness = (SeekBar)findViewById(R.id.sbBrightness);

        radioGroupDiffLevel =(RadioGroup) findViewById(R.id.rgDiffLevel);
        radioButtonEasy = (RadioButton)findViewById(R.id.rbEasy);
        radioButtonMedium = (RadioButton) findViewById(R.id.rbMedium);
        radioButtonMedium = (RadioButton) findViewById(R.id.rbHard);

        this.loadGameSetting();
    }

    private void loadGameSetting(){
        SharedPreferences sharedPreferences = (SharedPreferences) this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
        if (sharedPreferences != null){
            int brightness = sharedPreferences.getInt("brightness",90);
            int sound = sharedPreferences.getInt("sound",95);
            int checkedRadioButtonId = sharedPreferences.getInt("checkedRadioButtonId",R.id.rbMedium);

            this.seekBarSound.setProgress(sound);
            this.seekBarBrightness.setProgress(brightness);
            this.radioGroupDiffLevel.check(checkedRadioButtonId);
        }else {
            this.radioGroupDiffLevel.check(R.id.rbMedium);
            Toast.makeText(this,"Use the default game setting",Toast.LENGTH_SHORT).show();
        }
    }

    public void doSave(View view){

        // File chia sẻ sử dụng trong nội bộ ứng dụng, hoặc các ứng dụng được chia sẻ cùng ItemUser.
        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("brightness",this.seekBarBrightness.getProgress());
        editor.putInt("sound",this.seekBarSound.getProgress());

        //ID radioButton click
        int checkRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();
        editor.putInt("checkedRadioButtonId",checkRadioButtonId);

        //Save
        editor.apply();

        Toast.makeText(this,"Game Setting saved!",Toast.LENGTH_LONG).show();
    }



}
