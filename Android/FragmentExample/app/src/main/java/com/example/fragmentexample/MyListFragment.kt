package com.example.fragmentexample

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.ListFragment

class MyListFragment: ListFragment() {

    val itemsArrayList = arrayListOf("A","B","C","D","E","F","G","H","I","J")

    var itemChangeListener: ItemChangeListener? = null

    interface ItemChangeListener {
        //the selected item is changed
        fun onSelectedItemChanged(itemNameString: String)
        }

    fun setMyItemChangedListener(listener: ItemChangeListener){
        itemChangeListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ItemsArrayAdapter(
            this.requireContext(), R.layout.list_item, itemsArrayList)

        //list allow one item to be selected at a time
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.setBackgroundColor(Color.WHITE)
        listView.onItemClickListener = itemsOnClickListener
    }

    internal class ViewHolder{
        var itemTextView: TextView? = null
    }//ViewHolder

    //custom ArrayAdapter
    inner class ItemsArrayAdapter(context: Context, resource: Int,
        list: ArrayList<String>): ArrayAdapter<String>(context,resource,list){

        var resource: Int
        var list: ArrayList<String>
        var vi: LayoutInflater

        init {
            this.resource = resource
            this.list = list
            vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        }//init

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val holder: ViewHolder
            val retView: View

            //if convertView is null, inflate GUI and create a VIewHolder
            if (convertView == null){

                retView = vi.inflate(resource, null)
                holder = ViewHolder()
                holder.itemTextView = retView.findViewById(R.id.text1)
                retView.tag = holder

            } else { //get existing viewHolder
                //get viewHolder fron convertViews tag
                holder = convertView.tag as ViewHolder
                retView = convertView
            }

            val item = list[position] //get current item
            holder.itemTextView?.text = item
            return retView
        }

    }//ItemsArrayAdapter

    private val itemsOnClickListener: AdapterView.OnItemClickListener =
        AdapterView.OnItemClickListener{ adapterView, view, position, id ->
            itemChangeListener?.onSelectedItemChanged(
                (view as TextView).text.toString())
        }

}//MyListFragment