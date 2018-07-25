package com.sellkaro.ui.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.adapter.CameraRollAdapter
import com.sellkaro.constants.Constant
import kotlinx.android.synthetic.main.activity_camera_roll.*

class CameraRollActivity : BaseActivity(), RecyclerViewClickListener {


    private val reciever = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Constant.FINISH_ACTIVITY) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_roll)
        setSupportActionBar(toolbar)

        registerReceiver(reciever, IntentFilter(Constant.FINISH_ACTIVITY))
        init()
    }

    private fun init() {
        btn_Tips.setOnClickListener { showToast(context,"In process")}
        toolbar_Next.setOnClickListener { proceedToNext(ChooseCategoryActivity::class)}
        imgBack.setOnClickListener { finish() }
        setPostItmes()
    }

    private fun setPostItmes() {
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        var adapter = CameraRollAdapter(this,this)
        recyclerView.adapter = adapter
    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {

    }
}
