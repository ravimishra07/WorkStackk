package com.ravimishra.workstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    EditText goaldetail, workdetail,etvalDetail;
    Button btndel, btnUpdate;
    RvAdapter rvAdapter;
    MyHolder myHolder;
    ArrayList<Data> d = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvAdapter = new RvAdapter(this, d);
        goaldetail = findViewById(R.id.et_goal_detail);
        workdetail = findViewById(R.id.et_work_detail);
        etvalDetail = findViewById(R.id.et_value_detail);
        btndel = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        Intent i = getIntent();

        final String g = i.getExtras().getString("GOAL");
        final String w = i.getExtras().getString("GOALTYPE");
        final int id = i.getExtras().getInt("ID");
        final int val= i.getExtras().getInt("GVALUE");
        goaldetail.setText(g);
        workdetail.setText(w);
        etvalDetail.setText(""+val);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Delete(id);
                rvAdapter.notifyDataSetChanged();
                //rvAdapter.notifyItemRangeChanged();

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update(id, goaldetail.getText().toString(), workdetail.getText().toString(),Integer.parseInt(etvalDetail.getText().toString()));
            }
        });

    }

    public void Update(int id, String goal, String work,int value) {
        DbAdapter dbAdapter = new DbAdapter(this);
        dbAdapter.openDB();
        long result = dbAdapter.update(id, goal, work,value);
        if (result > 0) {
            goaldetail.setText(goal);
            workdetail.setText(work);
            etvalDetail.setText(value+"");
            Snackbar.make(goaldetail, "Updated Successfully", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(goaldetail, "Unable to update", Snackbar.LENGTH_SHORT).show();

        }
        dbAdapter.close();

    }

    public void Delete(int id) {

        DbAdapter dbAdapter = new DbAdapter(this);
        dbAdapter.openDB();
        int c = rvAdapter.getItemCount();
        long result = dbAdapter.Delete(id);
        finish();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
       //System.exit(0);
        if (result > 0) {
            this.finish();
//            if(rvAdapter.dataArrayList.size()==0){
//                System.exit(0);
//            }


            rvAdapter.notifyDataSetChanged();
        } else {

            Snackbar.make(goaldetail, "Unable to delete", Snackbar.LENGTH_SHORT).show();

        }
        dbAdapter.close();


    }
}