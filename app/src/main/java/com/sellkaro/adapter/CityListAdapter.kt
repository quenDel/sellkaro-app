package com.sellkaro.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.model.city.City


/**
 * Created by Pradeep on 01/01/2004.
 */

class CityListAdapter(private val list: List<City>,
                      private val clickListener: RecyclerViewClickListener) : RecyclerView.Adapter<CityListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_city_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.text.text = item.name
        holder.text.checkMarkDrawable = null

        if (item.selected) {
            holder.text.setCheckMarkDrawable(R.drawable.ic_check_circle)
        }

        holder.item.setOnClickListener {
            for (data in list) {
                data.selected = false
            }
            item.selected = true
            notifyDataSetChanged()
            clickListener.onClickRecyclerItem(holder.item, position, item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var text: CheckedTextView = itemView.findViewById<View>(R.id.item) as CheckedTextView

    }
}
