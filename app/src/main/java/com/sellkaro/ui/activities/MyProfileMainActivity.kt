package com.sellkaro.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.MotionEvent
import android.view.View
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.adapter.MyProfileProductAdapter
import com.sellkaro.constants.Constant
import kotlinx.android.synthetic.main.activity_my_profile_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MyProfileMainActivity : BaseActivity(), RecyclerViewClickListener {

    private val reciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Constant.FINISH_ACTIVITY) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile_main)
        registerReceiver(reciever, IntentFilter(Constant.FINISH_ACTIVITY))

        setSupportActionBar(toolbar)
        toolbar_title.text = "My Profile"
        imgBack.visibility = View.VISIBLE
        imgBack.setImageResource(R.drawable.ic_arrow_back_black_24dp)
        init()
    }

    private fun init() {
        setupPager()
        setPostItmes()
        ratingBar.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event?.action == MotionEvent.ACTION_UP) {
                showToast(context, "in process")
                return@OnTouchListener true
            }
            false
        })
        editProfile.setOnClickListener { showToast(context, "in process") }
        editSetting.setOnClickListener { showToast(context, "in process") }
        editOffersMade.setOnClickListener { showToast(context, "in process") }
    }

    private fun setupPager() {
        imgBack.setOnClickListener { onBackPressed() }
    }

    private fun setPostItmes() {
        recyclerViewPost.layoutManager = GridLayoutManager(context, 2)
        var adapter = MyProfileProductAdapter(context, this)
        recyclerViewPost.adapter = adapter
    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {
        showToast(context, "in process")
    }


}
