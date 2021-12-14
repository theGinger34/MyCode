package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    public var titles = mutableListOf<String>()
    public var details = mutableListOf<String>()
    public var images = mutableListOf<Int>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //need property for each item on rows view
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener { v: View ->

                var position: Int = absoluteAdapterPosition

                Snackbar.make(v, "Click detected on item ${position + 1}",
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                    .show()
            }


        }//init

    }//ViewHolder

    //inflate each row's view and get a view holder
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)

        return ViewHolder(v)
    }//onCreateViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }//onBindViewHolder

    override fun getItemCount(): Int {
        return titles.size
    }

}//RecyclerAdapter