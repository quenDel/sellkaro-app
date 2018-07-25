package com.sellkaro.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.model.city.ImageHolder
import com.sellkaro.ui.activities.BaseActivity


/**
 * Created by Pradeep on 01/01/2004.
 */

class CameraRollAdapter(val context: Context, private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var selected = 0

    val list = ArrayList<ImageHolder>()

    init {

        val item = ImageHolder()
        item.image = R.drawable.camera_with_bg
        item.count = 0
        item.isSelected = false
        list.add(item)

        for (i in 0..30) {
            val itme = ImageHolder()
            itme.image = R.drawable.img1
            itme.count = 0
            itme.isSelected = false
            list.add(itme)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_add_photo_camera, parent, false)
            ViewCameraHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_camera_roll_item, parent, false)
            ViewItemHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            /* holder.item.setOnClickListener {
                 clickListener.onClickRecyclerItem(holder.item, position, item)
             }*/
        } else {
            val holder = viewHolder as ViewItemHolder
            val item = list[position]
            holder.img.setImageResource(item.image)
            holder.layoutSelector.visibility = View.GONE


            if (list[position].isSelected) {
                holder.layoutSelector.visibility = View.VISIBLE
                holder.txtCount.text = list[position].count.toString()
            }

            holder.item.setOnClickListener {
                if (item.isSelected) {
                    item.isSelected = false
                    setActive()
                    notifyDataSetChanged()
                    clickListener.onClickRecyclerItem(holder.item, position, item)
                } else {
                    if (selected < 5) {
                        item.isSelected = true
                        setActive()
                        notifyDataSetChanged()
                        clickListener.onClickRecyclerItem(holder.item, position, item)
                    } else {
                        BaseActivity.showAlert(context, "You can select up to 5 Pictures")
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var layoutSelector: RelativeLayout = itemView.findViewById<View>(R.id.layoutSelector) as RelativeLayout
        var txtCount: TextView = itemView.findViewById<View>(R.id.txtCount) as TextView
        var img: ImageView = itemView.findViewById<View>(R.id.imgView) as ImageView
    }

    inner class ViewCameraHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            1
        } else {
            2
        }
    }

    fun setActive() {
        var count = 0
        for (i in list) {
            if (i.isSelected) {
                count++
                i.count = count
            }
        }
        selected = count
    }


}
