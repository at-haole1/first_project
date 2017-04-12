package com.example.kimhao.first_project.BroadcastReceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kimhao.first_project.R;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by KimHao on 28/03/2017.
 */

public class FragmentReceiver extends Fragment {
    int mStatus = 0;
    TextView logger;

    public FragmentReceiver() {
        // Required empty public constructor
    }

    public FragmentReceiver(int status) {
        mStatus = status;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_receiver, container, false);
        logger = (TextView) myView.findViewById(R.id.tvBattery);
        setstat(mStatus);
        return myView;
    }

    public void setstat( int s) {
        mStatus = s;
        String info = "";
        switch (s) {
            case 0:
                info= "\n should not get a zero, unless app just started";
                break;
            case 1:
                info= "\n batter low. should shut down things.";
                break;
            case 2:
                info= "\n battery Okay.  ";
                break;
            case 3:
                info= "\n Power connected, so phone is charging.";
                break;
            case 4:
                info= "\n Power disconnected.";
                break;
            default:
                info= "\n something wrong.";

        }
        logger.setText("status is " + mStatus+ info);
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder notifiBuilder = new Notification.Builder(getContext());
        notifiBuilder.setContentTitle("Battery");
        notifiBuilder.setContentText(logger.getText());
        notifiBuilder.setSmallIcon(R.drawable.ic_home_red_700_24dp);

        manager.notify(1,notifiBuilder.build());
    }

}
