package com.example.mrpassword.kin01;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by TnKstudio on 12/11/2560.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewItem>{
    List<Food> foodList;

    @Override
    public ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manu_item,parent,false);
        return new ViewItem(view);
    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
            Food food= foodList.get(position);
            holder.textView.setText(food.getName());
            Picasso.with(holder.imageView.getContext()).load(food.getPic()).into(holder.imageView);

    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public int getItemCount() {
        if (foodList == null)return 0;
        return foodList.size();
    }

    public class ViewItem extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewItem(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.menu_image);
            textView = itemView.findViewById(R.id.menu_name);
        }
    }


}
