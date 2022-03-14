package com.dev.androidarchitectures.data.network

import com.dev.androidarchitectures.data.model.Countries
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {

   @GET("all")
   fun getCountriesList(): Single<List<Countries>>



}