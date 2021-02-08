package com.example.infosysdemo.ui.deshbord.adepter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.infosysdemo.R
import com.example.infosysdemo.model.Row
import kotlinx.android.synthetic.main.row_item.view.*

class ListOfRowAdepter(var items: List<Row>, val clickLister: (Row) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {
    var row_index: Int = -1
    val TAG: String = "ListOfRowAdepter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.row_item, parent, false)
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
        view.title.text = item.title
        view.descrip.text=item.description
        try {

            Glide.with(this.view.context)
                .load(item.imageHref)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(view.imges)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}