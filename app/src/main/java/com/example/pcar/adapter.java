package com.example.pcar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private ArrayList<items> mList;


    public static  class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imgButton1;
        public TextView txt1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgButton1 = itemView.findViewById(R.id.imgbtn1);
            txt1 = itemView.findViewById(R.id.txt1);
        }
    }

    public adapter(ArrayList<items> List){

        mList = List;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
         ViewHolder evh = new ViewHolder(v);
         return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        items currentItem = mList.get(position);

        holder.imgButton1.setImageResource(currentItem.getmImageResource());
        holder.txt1.setText(currentItem.getmText1());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
