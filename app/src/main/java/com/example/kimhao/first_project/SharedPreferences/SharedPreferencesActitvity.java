package com.example.kimhao.first_project.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.kimhao.first_project.Interface.MyPrefs_;
import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_share_prefences)
public class SharedPreferencesActitvity extends AppCompatActivity {
    @ViewById(R.id.sbSound)
    SeekBar seekBarSound;

    @ViewById(R.id.sbBrightness)
    SeekBar seekBarBrightness;

    @ViewById(R.id.rgDiffLevel)
    RadioGroup radioGroupDiffLevel;

    @ViewById(R.id.rbEasy)
    RadioButton radioButtonEasy;

    @ViewById(R.id.rbMedium)
    RadioButton radioButtonMedium;

    @ViewById(R.id.rbHard)
    RadioButton radioButtonHard;

    @ViewById(R.id.btnSave)
    Button mBtnSave;

    @Pref
    MyPrefs_ myPrefs;

    @AfterViews
    void after(){
        seekBarSound.setProgress(myPrefs.sound().get());
        seekBarBrightness.setProgress(myPrefs.brightness().get());
        radioGroupDiffLevel.check(myPrefs.checkedRadioButtonId().get());

    }
    @Click(R.id.btnSave)
    void saveShared(){
//        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putInt("brightness",this.seekBarBrightness.getProgress());
//        editor.putInt("sound",this.seekBarSound.getProgress());
//
//        //ID radioButton click
//        int checkRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();
//        editor.putInt("checkedRadioButtonId",checkRadioButtonId);
//
//        //Save
//        editor.apply();
//
//        Toast.makeText(this,"Game Setting saved!",Toast.LENGTH_LONG).show();
        myPrefs.edit()
                .brightness()
                .put(seekBarBrightness.getProgress())
                .sound()
                .put(seekBarSound.getProgress())
                .checkedRadioButtonId()
                .put(radioGroupDiffLevel.getCheckedRadioButtonId())
                .apply();
        Log.e("aa", "saveShared: "+seekBarSound.getProgress()+"" );
        //myPrefs.clear();
    }
}
