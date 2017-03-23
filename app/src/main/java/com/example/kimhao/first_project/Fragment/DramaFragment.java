package com.example.kimhao.first_project.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimhao.first_project.Fragment.AdapterFragment.MyRecyclerViewAdapter;
import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.model.ItemUser;

import java.util.ArrayList;

/**
 * Created by KimHao on 23/03/2017.
 */

public class DramaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.drama_fragment,null);
        //RecyclerView
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.mRecyclerDrama);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(getDramaUser(),this.getActivity()));


        return v;
    }

    private ArrayList<ItemUser> getDramaUser() {
        ArrayList<ItemUser> users = new ArrayList<>();

        return users;

    }
    @Override
    public String toString() {
        return "Drama";
    }
}
