package com.sellkaro.interfaces

import android.view.View

interface RecyclerViewClickListener {
    fun onClickRecyclerItem(view: View, position: Int, item: Any)

}