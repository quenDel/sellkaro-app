package com.sellkaro.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.model.city.ImageHolder


/**
 * Created by Pradeep on 01/01/2004.
 */

class SelectedImageAdapter(val context: Context, private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val list = ArrayList<ImageHolder>()

    init {

        for (i in 0..3) {
            val itme = ImageHolder()
            itme.image = R.drawable.img1
            itme.count = 0
            itme.isSelected = false
            list.add(itme)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_add_photo_more, parent, false)
            ViewAddHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_single_image_item, parent, false)
            ViewItemHolder(view)
        }
    }

    override fun onBindViewHolder(viho: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {

        } else {
            val holder = viho as ViewItemHolder
            val item = list[position]
            holder.img.setImageResource(item.image)

            holder.item.setOnClickListener {
                clickListener.onClickRecyclerItem(holder.item, position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewAddHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class ViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var img: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == list.size-1) {
            1
        } else 2
    }

}
