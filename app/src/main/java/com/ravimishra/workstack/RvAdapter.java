package com.ravimishra.workstack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.Inflater;


public class RvAdapter extends RecyclerView.Adapter<MyHolder> implements View.OnLongClickListener,View.OnClickListener{
    Context context;
    ArrayList<Data> dataArrayList;
TextView tv1;
    public RvAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_layout, null);
        //notifyDataSetChanged ();
        //notifyItemRemoved(i);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final int min = 0;
         int max = dataArrayList.get(i).getValue();
        final int total = max - min;
        int count=0;
        int vl = dataArrayList.get(i).getValue();
        myHolder.goalTxt.setText(dataArrayList.get(i).getGoal());
        myHolder.goalTypeTxt.setText(dataArrayList.get(i).getGoalType());
        myHolder.valtxt.setText(String.valueOf(dataArrayList.get(i).getValue()));
        myHolder.seekBar.setMax(max);
        myHolder.fabInc.setOnClickListener(this);
        myHolder.fabDec.setOnClickListener(this);
        myHolder.fabInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Increment clicked", Toast.LENGTH_SHORT).show();
            }
        });
        myHolder.fabDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Decrement clicked", Toast.LENGTH_SHORT).show();
            }
        });
        try {


            myHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(View v, int pos) {

                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("GOAL", dataArrayList.get(pos).getGoal());
                    i.putExtra("GOALTYPE", dataArrayList.get(pos).getGoalType());
                    i.putExtra("ID", dataArrayList.get(pos).getId());
                    i.putExtra("GVALUE", dataArrayList.get(pos).getValue());
                    context.startActivity(i);
                }

                @Override
                public void onLongClick(View view, int pos) {

                }
            });
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public void close(int position) {
        dataArrayList.remove(position);
        notifyItemRemoved(position);
        if (dataArrayList.isEmpty())
            ((Activity) context).finish();
    }

    @Override
    public void onClick(View v) {
//        Data data =dataArrayList.get(i)
//     switch (v.getId()){
//         case R.id.fabIncrement:
//             Toast.makeText(context, "Increment Clicked", Toast.LENGTH_SHORT).show();
//             break;
//         case R.id.fabDecrement:
//             Toast.makeText(context, "Decrement Clicked", Toast.LENGTH_SHORT).show();
//             break;


    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
