package com.example.lihu.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.lihu.androidreporter.R;

/**
 * Created by lihu on 2015-9-29.
 */
public class FestivalFragment extends Fragment {
    private GridView mGridView;
//    private ArrayAdapter<Festval> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frament_festval,null);
        return v;
    }
}
