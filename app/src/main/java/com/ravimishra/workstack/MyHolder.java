package com.ravimishra.workstack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyHolder extends RecyclerView.ViewHolder  {
    TextView goalTxt,goalTypeTxt,valtxt;
    RvAdapter rvAdapter;
    ItemClickListener itemClickListener;
    ArrayList<Data> data=new ArrayList<>();
    Context context;
    public MyHolder(View itemView) {
        super(itemView);
       // this.notify();
        //Toast.makeText(context, " posss"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
        rvAdapter=new RvAdapter(context,data);
        rvAdapter.notifyDataSetChanged();
        goalTxt=itemView.findViewById(R.id.tv_goal);
        valtxt=itemView.findViewById(R.id.tv_value);
        goalTypeTxt=itemView.findViewById(R.id.tv_work_type);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(v,getAdapterPosition());

            }
        });
    }

//    @Override
//    public void onClick(View v) {
//   Toast.makeText(context, "poss"+getAdapterPosition(), Toast.LENGTH_SHORT).show();
//           this.itemClickListener.onItemClick(v,getAdapterPosition());
//    }
    public  void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
