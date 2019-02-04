package com.ravimishra.workstack.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ravimishra.workstack.Constants;
import com.ravimishra.workstack.Model.Data;
import com.ravimishra.workstack.Adapter.DbAdapter;
import com.ravimishra.workstack.R;
import com.ravimishra.workstack.Adapter.RvAdapter;
import com.ravimishra.workstack.SharedPref;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context c;
    String goal, workType;
    int workValue = 0,count=0;
    EditText etGoal, etwork, etval;
    int val;
    LinearLayout relativeLayout;
    SharedPref sharedPref;
    SharedPreferences.Editor editor;
    String titDialogbox = "Add a new goal..";
    RecyclerView rv;
    RvAdapter adapter;
    ArrayList<Data> data = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //tv=findViewById(R.id.tv);
        setSupportActionBar(toolbar);
        relativeLayout=findViewById(R.id.relLayout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        sharedPref=new SharedPref(this);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.new_dialog, null);
                dialogBuilder.setView(dialogView);

                etGoal = (EditText) dialogView.findViewById(R.id.et_goal);
                etwork = (EditText) dialogView.findViewById(R.id.et_work_type);
                etval = (EditText) dialogView.findViewById(R.id.et_value);
//                final NumberPicker numberPicker=dialogView.findViewById(R.id.np);
//                String[] numbers = new String[150/5];
//                for (int i=0; i<numbers.length; i++)
//                    numbers[i] = Integer.toString(i*5);
//                numberPicker.setDisplayedValues(numbers);
//                numberPicker.setMaxValue(numbers.length-1);
//                numberPicker.setMinValue(0);
//                numberPicker.setWrapSelectorWheel(false);
                TextView title_of_dialog = new TextView(getApplicationContext());
                //  title_of_dialog.setTypeface(typeface);
                dialogBuilder.setCustomTitle(title_of_dialog);
                // dialogBuilder.setTitle("Add a new goal..");
                //  dialogBuilder.setMessage("Enter text below");
                dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //do something with edt.getText().toString();

                        goal = etGoal.getText().toString();
                        workType = etwork.getText().toString();
                        val = Integer.parseInt(etval.getText().toString());

                        if (goal.matches("") || workType.matches("") || val == 0) {
                            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //workValue=numberPicker.getValue();
                        //tv.setText(goal+ "   "+workType+ "  "+workValue);

                        save(goal, workType, val);
                    }
                });
                dialogBuilder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        retrive();
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        adapter = new RvAdapter(this, data);


      //Toast.makeText(this, "no ", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();

        // tv.setVisibility(View.VISIBLE);
        retrive();


    }


    private void save(String goal, String workType, int val) {
        DbAdapter dbAdapter = new DbAdapter(this);

        dbAdapter.openDB();
        long result = dbAdapter.add(goal, workType, val);
        if (result > 0) {
            etGoal.setText("");
            etwork.setText("");
        } else {
            Snackbar.make(etGoal, "Unable To Insert", Snackbar.LENGTH_SHORT).show();

        }
        dbAdapter.close();
        retrive();
    }

    private void retrive() {
        DbAdapter dbAdapter = new DbAdapter(this);
        dbAdapter.openDB();

        data.clear();
        Cursor c = dbAdapter.getAllData();
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String goal = c.getString(1);
            String work = c.getString(2);
            int v = c.getInt(3);
            Data data1 = new Data(id, goal, work, v);
            data.add(data1);
        }
        if (!(data.size() < 1)) {
            rv.setAdapter(adapter);
relativeLayout.setVisibility(View.INVISIBLE);
          //  Toast.makeText(this, "size"+data.size(), Toast.LENGTH_SHORT).show();
        }
        if ((data.size() == 0)) {
            //rv.setAdapter(adapter);
       relativeLayout.setVisibility(View.VISIBLE);
       //     Toast.makeText(this, "size"+data.size(), Toast.LENGTH_SHORT).show();
         }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all) {
            DbAdapter dbAdapter = new DbAdapter(this);
            dbAdapter.openDB();
dbAdapter.DeleteAll();
adapter.notifyDataSetChanged();
dbAdapter.close();
sharedPref.deleteAll();
startActivity(new Intent(this,MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrive();
    }



}
