package com.sellkaro.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.widget.LinearLayout
import android.view.ViewGroup
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.sellkaro.R

class CustomPagerAdapter(mContext: Context) : PagerAdapter() {

    var mResources = intArrayOf(R.drawable.img1, R.drawable.img2)
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    override fun getCount(): Int {
        return mResources.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)

        val imageView = itemView.findViewById(R.id.imgView) as ImageView
        imageView.setImageResource(mResources[position])

        container.addView(itemView)

        return itemView
    }


}