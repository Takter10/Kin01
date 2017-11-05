package com.example.mrpassword.kin01;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.google.firebase.database.DatabaseReference;


/**
 * Created by MrPassword on 30/10/2560.
 */

public class RecyclerFragment extends Fragment {
    private int numberOfItem;
    private RecyclerView rv;
    private RVAdapter adapter;
    private Toolbar toolbar2;

    DatabaseReference Food ;
    public RecyclerFragment(){

    }

    public static RecyclerFragment newInstance(int numberOfItem){
        RecyclerFragment rvf = new RecyclerFragment();
        rvf.numberOfItem = numberOfItem;
        Bundle blackbox = new Bundle();
        blackbox.putInt("nof_item",numberOfItem);
        rvf.numberOfItem = numberOfItem;
        rvf.setArguments(blackbox);
        return rvf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler,container,false);

        toolbar2 = v.findViewById(R.id.toolbar2);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar2);
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);


        rv = v.findViewById(R.id.recycler);
        if(savedInstanceState != null)
            numberOfItem = getArguments().getInt("nof_item");
        adapter = new RVAdapter(numberOfItem);
        rv.setLayoutManager(new LinearLayoutManager(container.getContext()));
        rv.setAdapter(adapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).toggle.syncState();
    }

    private class RVAdapter extends RecyclerView.Adapter<VHolder>{
        int nof_item;
        RVAdapter(int nof_item){
            this.nof_item = nof_item;
        }
        @Override
        public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View card = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card,parent,false
                    );
            return new VHolder(card);
        }

        @Override
        public void onBindViewHolder(VHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return nof_item;
        }
    }

    private class VHolder extends RecyclerView.ViewHolder{
        public VHolder(View itemView) {
            super(itemView);
        }
    }
}
