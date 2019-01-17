package com.ravimishra.workstack;

import android.view.View;

public interface ItemClickListener {
    void onItemClick(View v, int pos);
    void onLongClick(View view,int position);
}
