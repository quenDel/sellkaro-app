package com.sellkaro.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sellkaro.interfaces.RecyclerViewClickListener
import com.sellkaro.R
import com.sellkaro.adapter.CityListAdapter
import com.sellkaro.model.city.City
import com.sellkaro.model.city.CityListReponse
import com.sellkaro.retrofits.RetrofitService
import com.sellkaro.retrofits.WebResponse
import com.sellkaro.utils.QuendelTransition
import kotlinx.android.synthetic.main.layout_recycler.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Response


class CityListActivity : BaseActivity(), RecyclerViewClickListener, WebResponse {

    val list = ArrayList<City>()
    var adapter: CityListAdapter? = null
    var city: City? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)

        setupToolbar()
        init()
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        imgBack.visibility = View.VISIBLE
        imgBack.setOnClickListener { onBackPressed() }
        toolbar_title.text = getString(R.string.selectCity)
    }

    private fun init() {
        toolbar_done.setOnClickListener { setCity() }
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CityListAdapter(list, this)
        recyclerView.adapter = adapter
        retrofitClient?.getCities(1)?.let { RetrofitService.getCities(getDialog(context), it, this) }
    }

    override fun onResponseSuccess(result: Response<*>) {
        list.clear()
        val response = result.body() as CityListReponse
        list.addAll(response.cities)
        adapter?.notifyDataSetChanged()
    }

    override fun onResponseFailed(error: String) {
        showToast(context, error)
    }

    override fun onClickRecyclerItem(view: View, position: Int, item: Any) {
        city = item as City
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        goBack()
    }

    private fun setCity() {
        if (city == null) showAlert(context, "Please select city")
        setResult(Activity.RESULT_OK, Intent().putExtra("CITY", city))
        goBack()
    }

    private fun goBack() {
        finish()
        QuendelTransition.setFadeTransitionToActivity(this)
    }

}
