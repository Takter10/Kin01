package com.example.mrpassword.kin01.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrpassword.kin01.Interface.ItemClickListener;
import com.example.mrpassword.kin01.R;

/**
 * Created by TnKstudio on 6/11/2560.
 */

public class RandomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtRandomName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;
    public RandomViewHolder(View itemView){
        super(itemView);
        txtRandomName = (TextView)itemView.findViewById(R.id.ranName);
        imageView = (ImageView)itemView.findViewById(R.id.ranImage);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public ItemClickListener getItemClickListener(){
        return  itemClickListener;
    }
    public RandomViewHolder(View itemView, ItemClickListener itemClickListener){
        super(itemView);
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
