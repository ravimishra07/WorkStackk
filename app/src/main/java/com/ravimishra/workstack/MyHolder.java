package com.ravimishra.workstack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class MyHolder extends RecyclerView.ViewHolder {
    TextView goalTxt, goalTypeTxt, valtxt,maxval;
    SeekBar seekBar;
    FloatingActionButton fabInc,fabDec;
    private final LinearLayout layout;
    final LinearLayout.LayoutParams params;
    RvAdapter rvAdapter;
    ItemClickListener itemClickListener;
    ArrayList<Data> data = new ArrayList<>();
    Context context;

    public MyHolder(View itemView) {
        super(itemView);
        // this.notify();
        //Toast.makeText(context, " posss"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
        rvAdapter = new RvAdapter(context, data);
        rvAdapter.notifyDataSetChanged();
        goalTxt = itemView.findViewById(R.id.tv_goal);
        seekBar= itemView.findViewById(R.id.seek_bar);
        valtxt = itemView.findViewById(R.id.tv_value);
        maxval = itemView.findViewById(R.id.maxval);
        fabDec = itemView.findViewById(R.id.fabDecrement);
        fabInc = itemView.findViewById(R.id.fabIncrement);
        goalTypeTxt = itemView.findViewById(R.id.tv_work_type);
        layout = (LinearLayout) itemView.findViewById(R.id.rv);



        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v, getAdapterPosition());

            }
        });
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



}
