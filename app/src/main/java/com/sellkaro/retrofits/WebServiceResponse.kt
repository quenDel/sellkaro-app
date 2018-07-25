package com.sellkaro.retrofits


import org.json.JSONException
import org.json.JSONObject

import retrofit2.Response

object WebServiceResponse {

    fun handleResponse(response: Response<*>, webResponse: WebResponse) {
        if (response.isSuccessful) {
            if (response.body() != null) {
                webResponse.onResponseSuccess(response)
            } else {
                webResponse.onResponseFailed(response.message())
            }
        } else {
            try {
                if (response.errorBody() != null) {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    webResponse.onResponseFailed(jObjError.getString("ExceptionMessage"))
                } else {
                    webResponse.onResponseFailed(response.message())
                }
            } catch (e: JSONException) {
                e.printStackTrace()
                webResponse.onResponseFailed(e.message.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                webResponse.onResponseFailed(e.message.toString())
            }

        }
    }

}