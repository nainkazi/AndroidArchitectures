package com.dev.androidarchitectures.mvvm



import androidx.lifecycle.ViewModel

class CountriesViewModel: ViewModel() {


    var repository: Repository = Repository()
    var listLiveData = repository.listLiveData
    var errorLiveData = repository.isErrorLiveData
    init {
        repository.getCountries()
    }

    fun onRetry() {
        repository.getCountries()
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
    }
}