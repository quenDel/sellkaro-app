package com.sellkaro.retrofits

import com.sellkaro.constants.Constant
import com.sellkaro.model.city.CityListReponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApiClient {

    @GET(Constant.ALL_CITIES)
    fun getCities(@Query("country_id") countryId:Int): Call<CityListReponse>

}
