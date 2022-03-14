package com.dev.androidarchitectures.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.dev.androidarchitectures.data.model.Countries
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration


class RetrofitClient {
    val BaseUrl = "https://restcountries.com/v3.1/"
    fun getRetrofitClient(): APIService {
        val okhttplogging = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
            .addInterceptor(okhttplogging)
            .callTimeout(Duration.ofSeconds(10000))
            .build()
        okhttplogging.level = HttpLoggingInterceptor.Level.BODY
       val retrofitClient =  Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofitClient.create(APIService::class.java)
    }

    fun getCountries(): Single<List<Countries>>{
        return getRetrofitClient().getCountriesList()
    }
}