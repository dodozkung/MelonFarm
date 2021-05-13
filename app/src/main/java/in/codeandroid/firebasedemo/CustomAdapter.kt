package com.farm.myapplication

import `in`.codeandroid.firebasedemo.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter     //กำหนดเป็น ArrayList เหมือนเดิมก็ได้
    (private val mContext: Context, private val mItems: List<CustomItem>) :
    RecyclerView.Adapter<CustomHolder>() {
    interface OnItemClickListener {
        fun onItemClick(item: View?, position: Int)
    }

    private var mListener: OnItemClickListener? = null
    fun setOnClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.custom_layout, parent, false)
        val viewHolder = CustomHolder(view)
        view.setOnClickListener { view ->
            if (mListener != null) {
                //                    int pos = viewHolder.getAdapterPosition();
                //                    String str = viewHolder.textView1.getText().toString();
                //                    str += "  :  " + viewHolder.textView2.getText().toString();
                //                    if(pos != RecyclerView.NO_POSITION) {
                //                        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                //                    }
                val pos = viewHolder.adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    mListener!!.onItemClick(view, pos)
                }
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: CustomHolder, position: Int) {
        val item = mItems[position]
        viewHolder.textView1.text = item.text1.toString()
    }
}