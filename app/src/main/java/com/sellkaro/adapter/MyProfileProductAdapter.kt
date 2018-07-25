package com.sellkaro.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.ui.activities.BaseActivity


/**
 * Created by Pradeep on 01/01/2004.
 */

class MyProfileProductAdapter(val context:Context,private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<MyProfileProductAdapter.ViewHolder>() {

    val list = arrayOf("Car & Bike", "Home $ Furniture", "Property", "Electronics", "Fashions", "Design & Craft", "Game & Sports",
            "Babies & Kids", "Book & Stationary", "Other")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_my_product_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        //holder.catName.text = item

        holder.item.setOnClickListener {
            clickListener.onClickRecyclerItem(holder.item, position, item)
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
        var layoutLikes: RelativeLayout = itemView.findViewById<View>(R.id.layoutLikes) as RelativeLayout
       // var img: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }
}
