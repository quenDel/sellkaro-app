package com.sellkaro.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.constants.Constant.Companion.FINISH_ACTIVITY
import com.sellkaro.ui.fragment.ListedSuccessFragment
import com.sellkaro.utils.QuendelTransition
import kotlinx.android.synthetic.main.activity_product_listing.*

class ListingProductActivity : BaseActivity(), RecyclerViewClickListener {

    var isSuccess = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_listing)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        btnDone.setOnClickListener { success() }
        imgBack.setOnClickListener { finish() }

    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {

    }

    fun success() {
        isSuccess = true
        val fragemnt = ListedSuccessFragment()
        val ft = supportFragmentManager.beginTransaction()
        QuendelTransition.setTransitionToFragment(ft)
        ft.replace(R.id.productListing, fragemnt)
        ft.commit()
    }

    override fun onBackPressed() {
        if (isSuccess) {
            sendBroadcast(Intent(FINISH_ACTIVITY))
            startActivity(Intent(context, AddPhotoActivity::class.java))
            finish()
        } else
            super.onBackPressed()
    }

}
