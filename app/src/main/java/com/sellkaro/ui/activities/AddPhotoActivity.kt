package com.sellkaro.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.sellkaro.R
import com.sellkaro.constants.Constant.Companion.FINISH_ACTIVITY
import kotlinx.android.synthetic.main.activity_add_photo.*

class AddPhotoActivity : BaseActivity() {

    private val reciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == FINISH_ACTIVITY) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photo)
        setSupportActionBar(toolbar)

        registerReceiver(reciever, IntentFilter(FINISH_ACTIVITY))
        init()
    }

    private fun init() {
        cardCamera.setOnClickListener { showAlert(context, "Camera action in development mode.") }
        cardGallery.setOnClickListener { proceedToNext(CameraRollActivity::class) }
        imgBack.setOnClickListener { finish() }
    }

}
