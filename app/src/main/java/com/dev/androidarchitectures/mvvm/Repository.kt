package com.dev.androidarchitectures.mvvm

import androidx.lifecycle.MutableLiveData
import com.dev.androidarchitectures.data.model.Countries
import com.dev.androidarchitectures.data.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Repository {
     var listLiveData = MutableLiveData<List<Countries>>()
     var isErrorLiveData = MutableLiveData<Boolean>()
     var compositeDisposable : CompositeDisposable = CompositeDisposable()
    fun getCountries(){
        val retrofitClient = RetrofitClient()
        compositeDisposable.add(retrofitClient.getRetrofitClient().getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       listLiveData.value = it
                       isErrorLiveData.value = false
            },{
                listLiveData.value = null
                isErrorLiveData.value = true
            }))

    }

    fun clear(){
        compositeDisposable.clear()
    }
}