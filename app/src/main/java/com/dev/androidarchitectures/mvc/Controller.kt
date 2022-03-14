package com.dev.androidarchitectures.mvc


import com.dev.androidarchitectures.data.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class Controller {
     var view: MVCActivity? = null
     var listNames = ArrayList<String>()
    constructor(view: MVCActivity){
        this.view =view
        getCountriesList()
    }

    private fun getCountriesList() {
        var retrofitClient = RetrofitClient()
        view?.compositeDisposable?.add(
            retrofitClient.getRetrofitClient().getCountriesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    if (it == null) {
                        view?.onError()
                    }else{
                        for(i in it.indices){
                            listNames.add(it[i].countriesName.countryName!!)
                        }
                        view?.onSuccess(listNames)
                    }
                 }, {
                      view?.onError()
                 })
        )
    }

   fun refresh(){
      getCountriesList()
  }
}


