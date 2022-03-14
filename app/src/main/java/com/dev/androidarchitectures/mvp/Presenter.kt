package com.dev.androidarchitectures.mvp

import com.dev.androidarchitectures.data.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Presenter {
    var list: ArrayList<String>
    var view: View? = null
    var compositeDisposable:CompositeDisposable

    constructor(view: View){
        this.view = view
        list = ArrayList()
        compositeDisposable = CompositeDisposable()
        getCountriesList()
    }

    private fun getCountriesList() {
        val retrofitClient = RetrofitClient()
        compositeDisposable.add(retrofitClient.getRetrofitClient().getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    if(it != null){
                        for (en in it.indices){
                            list.add(it.get(en).countriesName.countryName!!)
                        }
                        view?.onSuccess(list)
                    }
            },{
                view?.onError()
            }))
    }

    fun refresh(){
        getCountriesList()
    }

    interface View {
        fun onSuccess(list: ArrayList<String>)
        fun onError()
    }

   fun onClear(){
       compositeDisposable.dispose()
   }
}