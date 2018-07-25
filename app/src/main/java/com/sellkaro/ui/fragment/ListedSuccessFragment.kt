package com.sellkaro.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sellkaro.R
import com.sellkaro.constants.Constant
import com.sellkaro.ui.activities.AddPhotoActivity
import com.sellkaro.ui.activities.BaseActivity
import com.sellkaro.ui.activities.HomeActivity
import com.sellkaro.utils.QuendelTransition
import kotlinx.android.synthetic.main.fragment_listed_success.*

class ListedSuccessFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_listed_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.sendBroadcast(Intent(Constant.FINISH_ACTIVITY))
        init()
    }

    fun init() {
        ListAnotherProgram.setOnClickListener {
            startActivity(Intent(activity, AddPhotoActivity::class.java))
            activity?.finish()
            QuendelTransition.setFadeTransitionToActivity(activity)
        }
        txtNotNow.setOnClickListener { openNext() }
        imgClose.setOnClickListener { openNext() }
    }

    private fun openNext() {
        context?.let { BaseActivity.showToast(it, "In Process") }
        activity?.finish()
    }
}