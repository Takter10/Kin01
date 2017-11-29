package com.example.mrpassword.kin01;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MrPassword on 30/10/2560.
 */

public class LibaryFragment extends Fragment {
//
//    public LibaryFragment() {
//    }

    public static LibaryFragment newInstance(String text) {
        LibaryFragment lf = new LibaryFragment();
        Bundle blackbox = new Bundle();
        blackbox.putString("text", text);
        lf.setArguments(blackbox);
        return lf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_libary, container, false);

        LinearLayout FR = (LinearLayout)v.findViewById(R.id.FR);
        FR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("FR");
                startActivity(homeInent);
            }
        });
        LinearLayout FN = (LinearLayout)v.findViewById(R.id.FN);
        FN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("FN");
                startActivity(homeInent);
            }
        });
        LinearLayout FD = (LinearLayout)v.findViewById(R.id.FD);
        FD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("FD");
                startActivity(homeInent);
            }
        });
        LinearLayout cd = (LinearLayout)v.findViewById(R.id.RestCD);
        cd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("Rest0");
                startActivity(homeInent);
            }
        });
        LinearLayout nv = (LinearLayout) v.findViewById(R.id.RestNV);
        nv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("Rest1");
                startActivity(homeInent);
            }
        });
        LinearLayout az = (LinearLayout)v.findViewById(R.id.RestAz);
        az.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("Rest2");
                startActivity(homeInent);
            }
        });
        LinearLayout jb = (LinearLayout)v.findViewById(R.id.RestJB);
        jb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent homeInent = new Intent(getActivity(),Home.class);
                homeInent.setType("Rest3");
                startActivity(homeInent);
            }
        });
        return v;
    }





}
