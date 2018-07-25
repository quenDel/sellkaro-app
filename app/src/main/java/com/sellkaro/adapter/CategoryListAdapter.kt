package com.sellkaro.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.model.city.CategoryHolder


/**
 * Created by Pradeep on 01/01/2004.
 */

class CategoryListAdapter(private val clickListener: RecyclerViewClickListener)
    : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    val listd = arrayOf("Car & Bike", "Home $ Furniture", "Property", "Electronics", "Fashions", "Design & Craft", "Game & Sports",
            "Babies & Kids", "Book & Stationary", "Other")
    private var selectedPosition = -1


    val list = ArrayList<CategoryHolder>()
    init {
        for (i in listd) {
            val j = CategoryHolder()
            j.name = i
            j.isSelected = false
            list.add(j)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_category_linear_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.rbCategory.text = item.name


        //check the radio button if both position and selectedPosition matches
        holder.rbCategory.setChecked(position == selectedPosition);


        //Set the position tag to both radio button and label
        holder.rbCategory.setTag(position)
        holder.rbCategory.setOnClickListener(View.OnClickListener { v -> itemCheckChanged(v) })

    }


    //On selecting any view set the current position to selectedPositon and notify adapter
    private fun itemCheckChanged(v: View) {
        selectedPosition = v.tag as Int
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
        var rbCategory: RadioButton = itemView.findViewById<View>(R.id.rbCategory) as RadioButton
    }
}
