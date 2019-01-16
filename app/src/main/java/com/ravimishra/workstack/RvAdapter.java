package com.ravimishra.workstack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.Inflater;

public class RvAdapter extends RecyclerView.Adapter<MyHolder> {
    Context context;
    ArrayList<Data> dataArrayList;

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

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {


        myHolder.goalTxt.setText(dataArrayList.get(i).getGoal());
        myHolder.goalTypeTxt.setText(dataArrayList.get(i).getGoalType());
//        myHolder.valtxt.setText(dataArrayList.get(i).getValue());
        myHolder.valtxt.setText(String.valueOf(dataArrayList.get(i).getValue()));
       // Integer.toString(items[position].getRollNo())

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
}
