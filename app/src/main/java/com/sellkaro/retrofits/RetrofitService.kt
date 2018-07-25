package com.sellkaro.retrofits


import com.sellkaro.constants.Constant
import com.sellkaro.model.city.CityListReponse
import com.sellkaro.utils.AppProgressDialog
import dmax.dialog.SpotsDialog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        client = retrofit.create(RetrofitApiClient::class.java)
    }

    companion object {
        var client: RetrofitApiClient? = null

        val rxClient: RetrofitApiClient
            get() {
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constant.BASE_URL)
                        .build()
                return retrofit.create(RetrofitApiClient::class.java)
            }

        val retrofit: RetrofitApiClient?
            get() {
                if (client == null)
                    RetrofitService()

                return client
            }


        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        fun getResponseBody(dialog: SpotsDialog?, method: Call<ResponseBody>, webResponse: WebResponse) {
            AppProgressDialog.show(dialog)

            method.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    AppProgressDialog.hide(dialog)
                    WebServiceResponse.handleResponse(response, webResponse)
                }

                override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                    AppProgressDialog.hide(dialog)
                    throwable.message?.let { webResponse.onResponseFailed(it) }
                }
            })
        }

        /****
         * SERVER CALL METHODS
         */

        fun getCities(dialog: SpotsDialog?, method: Call<CityListReponse>, webResponse: WebResponse) {
            AppProgressDialog.show(dialog)
            method.enqueue(object : Callback<CityListReponse> {
                override fun onResponse(call: Call<CityListReponse>, response: Response<CityListReponse>) {
                    AppProgressDialog.hide(dialog)
                    WebServiceResponse.handleResponse(response, webResponse)
                }

                override fun onFailure(call: Call<CityListReponse>, throwable: Throwable) {
                    AppProgressDialog.hide(dialog)
                    throwable.message?.let { webResponse.onResponseFailed(it) }
                }
            })
        }
    }

}

