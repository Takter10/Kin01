package com.example.mrpassword.kin01;

import android.support.v4.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MrPassword on 30/10/2560.
 */

public class AccountFragment extends Fragment {

    public AccountFragment() {
    }

    public static AccountFragment newInstance(String text) {
        AccountFragment sf = new AccountFragment();
        Bundle blackbox = new Bundle();
        blackbox.putString("text", text);
        sf.setArguments(blackbox);
        return sf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);
        return v;

    }

}
