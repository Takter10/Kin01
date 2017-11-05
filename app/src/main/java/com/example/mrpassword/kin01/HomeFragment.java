package com.example.mrpassword.kin01;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LibaryFragment newInstance(String text) {
        LibaryFragment lf = new LibaryFragment();
        Bundle blackbox = new Bundle();
        blackbox.putString("text", text);
        lf.setArguments(blackbox);
        return lf;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;

    }
}
