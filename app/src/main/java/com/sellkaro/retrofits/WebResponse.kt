package com.sellkaro.retrofits

import retrofit2.Response

interface WebResponse {
    fun onResponseSuccess(result: Response<*>)

    fun onResponseFailed(error: String)
}