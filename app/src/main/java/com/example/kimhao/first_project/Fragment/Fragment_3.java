package com.example.kimhao.first_project.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimhao.first_project.R;
import com.example.kimhao.first_project.Model.ItemUser;

import java.util.ArrayList;

/**
 * Created by KimHao on 23/03/2017.
 */

public class Fragment_3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3,null);

        //RecyclerView
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.mRecyclerDoc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //recyclerView.setAdapter(new MyRecyclerViewAdapter(getDocMovies(),this.getActivity()));
        return v;
    }

    private ArrayList<ItemUser> getDocMovies() {
        ArrayList<ItemUser> users = new ArrayList<>();

        return users;

    }

    @Override
    public String toString() {
        return "Documentary";
    }
}
