package com.sellkaro.ui.activities

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.adapter.CategoryAdapterAdapter
import com.sellkaro.adapter.CustomPagerAdapter
import com.sellkaro.adapter.ItemAdapterAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_bottom_bar.*
import kotlinx.android.synthetic.main.layout_toolbar_home.*

class HomeActivity : BaseActivity(), RecyclerViewClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        setupPager()
        setCategory()
        setPostItmes()
        imgLike.setOnClickListener {
            showToast(context, "in process")
        }
        imgMsg.setOnClickListener {
            showToast(context, "in process")
        }
    }

    override fun onResume() {
        super.onResume()
        setActive(txtBrowse,imgViewBrowse)
    }

    private fun setupPager() {
        val adapter = CustomPagerAdapter(context)
        viewPager.adapter = adapter
        indicator.setViewPager(viewPager)

        layoutBrowse.setOnClickListener {
            setActive(txtBrowse,imgViewBrowse)
        }
        layoutMe.setOnClickListener {
            setActive(txtME,imgViewME)
            proceedToNext(MyProfileMainActivity::class)
        }
        fab.setOnClickListener {
            setActive(txtSELL,null)
            proceedToNext(AddPhotoActivity::class)
        }
        edtSearch.setOnClickListener { showToast(context, "in process") }
    }

    private fun setCategory() {
        recyclerViewCategory.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.HORIZONTAL, false)
        var adapter = CategoryAdapterAdapter(object : RecyclerViewClickListener {
            override fun onClickRecyclerItem(view: View, position: Int, item: Any) {
                showToast(context, "in process")
            }
        })
        recyclerViewCategory.adapter = adapter
    }

    private fun setPostItmes() {
        recyclerViewPost.layoutManager = GridLayoutManager(context, 2)
        var adapter = ItemAdapterAdapter(context, this)
        recyclerViewPost.adapter = adapter
    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {
        proceedToNext(ProductDetailsActivity::class)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.actionBrowse -> {
                showToast(context, "In process")
                true
            }
            R.id.actionSell -> {
                proceedToNext(AddPhotoActivity::class)
                true
            }
            R.id.actionMe -> {
                showToast(context, "In process")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActive(view: TextView, imageView: ImageView?) {
        txtBrowse.setTextColor(resources.getColor(R.color.signuptextfieldcolor))
        txtME.setTextColor(resources.getColor(R.color.signuptextfieldcolor))
        txtSELL.setTextColor(resources.getColor(R.color.signuptextfieldcolor))
        imgViewBrowse.setImageDrawable(resources.getDrawable(R.drawable.ic_browse))
        imgViewME.setImageDrawable(resources.getDrawable(R.drawable.ic_my_store))
        when (imageView) {
            imgViewBrowse -> {
                imgViewBrowse.setImageDrawable(resources.getDrawable(R.drawable.ic_browse_activated))
            }
            imgViewME -> {
                imgViewME.setImageDrawable(resources.getDrawable(R.drawable.ic_my_store_activate))
            }
        }
        view.setTextColor(resources.getColor(R.color.colorPrimary))
    }
}
