package com.sellkaro.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R


/**
 * Created by Pradeep on 01/01/2004.
 */

class CategoryAdapterAdapter(private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<CategoryAdapterAdapter.ViewHolder>() {

    val list = arrayOf("Car & Bike", "Home $ Furniture", "Property", "Electronics", "Fashions", "Design & Craft", "Game & Sports",
            "Babies & Kids", "Book & Stationary", "Other")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_category_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.catName.text = item

        holder.item.setOnClickListener {
            clickListener.onClickRecyclerItem(holder.item, position, item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var catName: TextView = itemView.findViewById<View>(R.id.catName) as TextView
        var img: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }
}
