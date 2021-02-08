package com.example.infosysdemo.ui.deshbord.adepter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.infosysdemo.R
import com.example.infosysdemo.model.Row

class ListOfRowAdepter (var items: List<Row>, val clickLister: (Row) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    var row_index: Int = -1
    val TAG: String = "ListOfRowAdepter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.row_item, parent,false)
        return MyViewHolder(
            listItem
        )
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "items size=" + items.size)
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items.get(position))


    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Row) {



    }

}