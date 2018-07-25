package com.sellkaro.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.adapter.CategoryListAdapter
import com.sellkaro.adapter.SelectedImageAdapter
import com.sellkaro.constants.Constant
import kotlinx.android.synthetic.main.activity_select_category.*

class ChooseCategoryActivity : BaseActivity(), RecyclerViewClickListener {


    private val reciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Constant.FINISH_ACTIVITY) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)
        setSupportActionBar(toolbar)

        registerReceiver(reciever, IntentFilter(Constant.FINISH_ACTIVITY))
        init()
    }

    private fun init() {
        toolbar_Next.setOnClickListener { proceedToNext(ListingProductActivity::class)}
        imgBack.setOnClickListener { onBackPressed() }
        setPostItmes()
        setCategory()
    }

    private fun setCategory() {
        recyclerViewCategory.layoutManager = LinearLayoutManager(context)
        var adapter = CategoryListAdapter(this)
        recyclerViewCategory.adapter = adapter
    }

    private fun setPostItmes() {
        recyclerView.layoutManager =  LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
        var adapter = SelectedImageAdapter(this,this)
        recyclerView.adapter = adapter
    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {

    }
}
