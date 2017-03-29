package com.example.kimhao.first_project.Alarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

/**
 * Created by KimHao on 28/03/2017.
 */

public class AlarmFragment extends Fragment {
    TextView logger = null;
    String myText = "";

    public AlarmFragment() {
        myText = "";
    }

    public AlarmFragment(String item){
        myText = item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noti,container,false);

        logger = (TextView) view.findViewById(R.id.tvLogger);
        logger.setText(myText);

        view.findViewById(R.id.btnSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AlarmActivity) getActivity()).setalarm();
            }
        });
        return view;
    }
}
