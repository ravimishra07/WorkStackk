package com.ravimishra.workstack.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.ravimishra.workstack.Activity.DetailActivity;
import com.ravimishra.workstack.Activity.MainActivity;
import com.ravimishra.workstack.Model.Data;
import com.ravimishra.workstack.R;
import com.ravimishra.workstack.SharedPref;

import java.util.ArrayList;


public class RvAdapter extends RecyclerView.Adapter<MyHolder> {
    Context context;
    ArrayList<Data> dataArrayList;
    TextView tv1;
    SharedPref sharedPref;
int v;
    public RvAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_layout, null);
        //notifyDataSetChanged ();
        //notifyItemRemoved(i);
        sharedPref=new SharedPref(context);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
         v=sharedPref.getInt(""+i,context);
if(v!=0){
    myHolder.valtxt.setText(""+v);
    myHolder.seekBar.setProgress(v);
}
else {
    myHolder.valtxt.setText("0");

    myHolder.seekBar.setProgress(0);
}
myHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        myHolder.valtxt.setText(""+progress);

        sharedPref.saveInt(context,""+i,progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
});
        myHolder.goalTxt.setText(dataArrayList.get(i).getGoal());
        myHolder.goalTypeTxt.setText(dataArrayList.get(i).getGoalType());

        myHolder.maxval.setText(String.valueOf(dataArrayList.get(i).getValue()));
        myHolder.seekBar.setMax(dataArrayList.get(i).getValue());
        // myHolder.seekBar.setProgress(Integer.parseInt(String.valueOf(myHolder.valtxt.getText())));

        myHolder.fabInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(myHolder.valtxt.getText()));
                if (count < Integer.parseInt(String.valueOf(myHolder.maxval.getText()))) {
                    count++;
                    myHolder.valtxt.setText(String.valueOf(count));
                    myHolder.seekBar.setProgress(count);
                    sharedPref.saveInt(context,""+i,count);
                }
              //  Toast.makeText(context, ""+sharedPref.getInt(""+i,context), Toast.LENGTH_SHORT).show();
            }
        });
        myHolder.fabDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(String.valueOf(myHolder.valtxt.getText()));
                if (count > 0)
                    count--;
                myHolder.valtxt.setText(String.valueOf(count));
                myHolder.seekBar.setProgress(count);

            }
        });

        myHolder.btnIconDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbAdapter dbAdapter = new DbAdapter(context);
                dbAdapter.openDB();
                long result = dbAdapter.Delete(dataArrayList.get(i).getId());

                if (result > 0) {
//
                    notifyDataSetChanged();
                } else {

                    Toast.makeText(context, "Unable to Delete", Toast.LENGTH_SHORT).show();
                }
                dbAdapter.close();
                sharedPref.del(""+i,context);
                notifyDataSetChanged();
                context.startActivity(new Intent(context, MainActivity.class));
            }
        });

        myHolder.btnIconEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("GOAL", dataArrayList.get(i).getGoal());
                intent.putExtra("GOALTYPE", dataArrayList.get(i).getGoalType());
                intent.putExtra("ID", dataArrayList.get(i).getId());
                intent.putExtra("GVALUE", dataArrayList.get(i).getValue());
                context.startActivity(intent);
            }
        });


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

