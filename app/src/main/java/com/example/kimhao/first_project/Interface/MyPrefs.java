package com.example.kimhao.first_project.Interface;

import com.example.kimhao.first_project.R;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by KimHao on 4/12/2017.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface MyPrefs {

    @DefaultInt(90)
    int brightness();

    @DefaultInt(95)
    int sound();

    @DefaultInt(R.id.rbMedium)
    int checkedRadioButtonId();

}
