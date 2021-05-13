package com.farm.myapplication

import `in`.codeandroid.firebasedemo.R
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class CustomHolder(view: View) : ViewHolder(view) {
    var textView1: TextView

    init {
        textView1 = view.findViewById(R.id.Modeshowtext)
    }
}