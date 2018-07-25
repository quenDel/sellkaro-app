package com.sellkaro.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.ui.activities.BaseActivity

/**
 * Created by Pradeep on 01/01/2004.
 */

class ItemAdapterAdapter(val context: Context,private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<ItemAdapterAdapter.ViewHolder>() {

    val list = arrayOf("Car & Bike", "Home $ Furniture", "Property", "Electronics", "Fashions", "Design & Craft", "Game & Sports",
            "Babies & Kids", "Book & Stationary", "Other")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_postitem_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        //holder.catName.text = item

        holder.item.setOnClickListener {
            clickListener.onClickRecyclerItem(holder.item, position, item)
        }
        holder.layoutSeller.setOnClickListener {
            BaseActivity.showToast(context, "In process")
        }
        holder.layoutLikes.setOnClickListener {
            BaseActivity.showToast(context, "In process")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var layoutSeller: RelativeLayout = itemView.findViewById<View>(R.id.layoutSeller) as RelativeLayout
        var layoutLikes: RelativeLayout = itemView.findViewById<View>(R.id.layoutLikes) as RelativeLayout
        // var img: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }
}
