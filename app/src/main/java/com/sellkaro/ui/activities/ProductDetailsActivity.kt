package com.sellkaro.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.sellkaro.R
import com.sellkaro.constants.Constant
import kotlinx.android.synthetic.main.single_product.*

class ProductDetailsActivity : BaseActivity() {

    private val reciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Constant.FINISH_ACTIVITY) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_product)
        init()
    }

    private fun init() {
        setupPager()

        ratingBar.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event?.action == MotionEvent.ACTION_UP) {
                showToast(context, "in process")
                return@OnTouchListener true
            }
            false
        })

    }

    private fun setupPager() {
        imgBack.setOnClickListener { onBackPressed() }
        btnMakeOffer.setOnClickListener { showToast(context, "in process") }
        chat.setOnClickListener { showToast(context, "in process") }
        txtUsername.setOnClickListener { showToast(context, "in process") }
        sellerName.setOnClickListener { showToast(context, "in process") }
        like.setOnClickListener { showToast(context, "in process") }
    }
}
